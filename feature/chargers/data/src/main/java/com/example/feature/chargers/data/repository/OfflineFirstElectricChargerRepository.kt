package com.example.feature.chargers.data.repository

import android.location.Location
import com.example.core.database.dao.AddressInfoDao
import com.example.core.database.dao.CommentTypeDao
import com.example.core.database.dao.ConnectionDao
import com.example.core.database.dao.ConnectionTypeDao
import com.example.core.database.dao.CurrentTypeDao
import com.example.core.database.dao.DataProviderDao
import com.example.core.database.dao.LevelDao
import com.example.core.database.dao.MediaItemDao
import com.example.core.database.dao.PointOfInterestConnectionDao
import com.example.core.database.dao.PointOfInterestDao
import com.example.core.database.dao.StatusTypeDao
import com.example.core.database.dao.UsageTypeDao
import com.example.core.database.dao.UserCommentDao
import com.example.core.database.dao.UserDao
import com.example.core.database.model.PointOfInterestConnectionEntity
import com.example.core.database.model.PopulatedPointOfInterest
import com.example.core.model.PointOfInterest
import com.example.feature.chargers.data.api.ElectricChargerApiClient
import com.example.feature.chargers.data.api.PointOfInterestDto
import com.example.feature.chargers.data.mapper.AddressInfoMapper
import com.example.feature.chargers.data.mapper.CommentTypeMapper
import com.example.feature.chargers.data.mapper.ConnectionMapper
import com.example.feature.chargers.data.mapper.ConnectionTypeMapper
import com.example.feature.chargers.data.mapper.CurrentTypeMapper
import com.example.feature.chargers.data.mapper.DataProviderMapper
import com.example.feature.chargers.data.mapper.LevelMapper
import com.example.feature.chargers.data.mapper.MediaItemMapper
import com.example.feature.chargers.data.mapper.PointOfInterestMapper
import com.example.feature.chargers.data.mapper.StatusTypeMapper
import com.example.feature.chargers.data.mapper.UsageTypeMapper
import com.example.feature.chargers.data.mapper.UserCommentMapper
import com.example.feature.chargers.data.mapper.UserMapper
import com.example.feature.chargers.domain.ElectricChargerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineFirstElectricChargerRepository @Inject constructor(
    private val electricChargerApiClient: ElectricChargerApiClient,
    private val addressInfoDao: AddressInfoDao,
    private val usageTypeDao: UsageTypeDao,
    private val pointOfInterestDao: PointOfInterestDao,
    private val userDao: UserDao,
    private val commentTypeDao: CommentTypeDao,
    private val userCommentDao: UserCommentDao,
    private val mediaItemDao: MediaItemDao,
    private val dataProviderDao: DataProviderDao,
    private val statusTypeDao: StatusTypeDao,
    private val connectionDao: ConnectionDao,
    private val levelDao: LevelDao,
    private val connectionTypeDao: ConnectionTypeDao,
    private val currentTypeDao: CurrentTypeDao,
    private val pointOfInterestConnectionDao: PointOfInterestConnectionDao,
) : ElectricChargerRepository {

    override val pointOfInterests = pointOfInterestDao.getPointOfInterests()
        .map { it.map(PopulatedPointOfInterest::toDomainModel) }

    override suspend fun getPointOfInterests(currentLocation: Location): Flow<List<PointOfInterest>> {
        try {
            val pointOfInterests = fetchPointOfInterests(currentLocation)
            saveLocalDatasource(pointOfInterests)
        } catch (exception: UnknownHostException) {
            println("ElectricChargerRepository: Unable to fetch remote datasource")
        }
        return pointOfInterests
    }

    private suspend fun fetchPointOfInterests(currentLocation: Location): List<PointOfInterestDto> =
        electricChargerApiClient.getNearestElectricChargers(currentLocation)


    private fun saveLocalDatasource(pointOfInterestDtos: List<PointOfInterestDto>) {
        pointOfInterestDtos.map {
            usageTypeDao.truncateTable()
            statusTypeDao.truncateTable()
            dataProviderDao.truncateTable()
            levelDao.truncateTable()
            currentTypeDao.truncateTable()
            connectionTypeDao.truncateTable()
            connectionDao.truncateTable()
            addressInfoDao.truncateTable()
            mediaItemDao.truncateTable()
            userCommentDao.truncateTable()
            commentTypeDao.truncateTable()
            userDao.truncateTable()
            pointOfInterestDao.truncateTable()
            pointOfInterestConnectionDao.truncateTable()
            it
        }
            .onEach {
                usageTypeDao.saveUsageType(UsageTypeMapper.toEntityModel(it.UsageType))
                dataProviderDao.saveDataProvider(DataProviderMapper.toEntityModel(it.DataProvider))
                statusTypeDao.saveStatusType(StatusTypeMapper.toEntityModel(it.StatusType))

                addressInfoDao.saveAddressInfo(AddressInfoMapper.toEntityModel(it.AddressInfo, it))
                pointOfInterestDao.savePointOfInterest(PointOfInterestMapper.toEntityModel(it))
                it.Connections?.onEach { connectionDto ->
                    levelDao.saveLevel(LevelMapper.toEntityModel(connectionDto.Level))
                    if (connectionDto.CurrentType != null) {
                        currentTypeDao.saveCurrentType(CurrentTypeMapper.toEntityModel(connectionDto.CurrentType))
                    }
                    connectionTypeDao.saveConnectionType(
                        ConnectionTypeMapper.toEntityModel(
                            connectionDto.ConnectionType
                        )
                    )
                    connectionDao.saveConnection(ConnectionMapper.toEntityModel(connectionDto))
                    pointOfInterestConnectionDao.savePointOfInterestConnections(
                        PointOfInterestConnectionEntity(
                            connectionId = connectionDto.ID,
                            poiId = it.ID
                        )
                    )
                }
                it.MediaItems?.onEach { mediaItemDto ->
                    userDao.saveUser(UserMapper.toEntityModel(mediaItemDto.User))
                    mediaItemDao.saveMediaItem(MediaItemMapper.toEntityModel(mediaItemDto))
                }
                it.UserComments?.onEach { userCommentDto ->
                    commentTypeDao.saveCommentType(CommentTypeMapper.toEntityModel(userCommentDto.CommentType))
                    userCommentDao.saveUserComment(UserCommentMapper.toEntityModel(userCommentDto))
                }
            }
    }
}