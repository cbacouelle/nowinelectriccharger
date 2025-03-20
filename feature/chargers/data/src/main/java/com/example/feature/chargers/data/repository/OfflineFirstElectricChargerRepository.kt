package com.example.feature.chargers.data.repository

import android.location.Location
import com.example.core.database.dao.AddressInfoDao
import com.example.core.database.dao.CommentTypeDao
import com.example.core.database.dao.MediaItemDao
import com.example.core.database.dao.PointOfInterestDao
import com.example.core.database.dao.UserCommentDao
import com.example.core.database.dao.UserDao
import com.example.core.database.model.PopulatedPointOfInterest
import com.example.core.model.PointOfInterest
import com.example.feature.chargers.data.api.ElectricChargerApiClient
import com.example.feature.chargers.data.api.PointOfInterestDto
import com.example.feature.chargers.data.mapper.AddressInfoMapper
import com.example.feature.chargers.data.mapper.CommentTypeMapper
import com.example.feature.chargers.data.mapper.MediaItemMapper
import com.example.feature.chargers.data.mapper.PointOfInterestMapper
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
    private val pointOfInterestDao: PointOfInterestDao,
    private val userDao: UserDao,
    private val commentTypeDao: CommentTypeDao,
    private val userCommentDao: UserCommentDao,
    private val mediaItemDao: MediaItemDao,
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
            addressInfoDao.truncateTable()
            mediaItemDao.truncateTable()
            userCommentDao.truncateTable()
            commentTypeDao.truncateTable()
            userDao.truncateTable()
            pointOfInterestDao.truncateTable()
            it
        }
            .onEach {
                addressInfoDao.saveAddressInfo(AddressInfoMapper.toEntityModel(it.AddressInfo, it))
                pointOfInterestDao.savePointOfInterest(PointOfInterestMapper.toEntityModel(it))
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