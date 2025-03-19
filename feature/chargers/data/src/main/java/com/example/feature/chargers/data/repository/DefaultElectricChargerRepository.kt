package com.example.feature.chargers.data.repository

import android.location.Location
import com.example.core.database.dao.UserCommentDao
import com.example.core.database.dao.UserDao
import com.example.core.database.model.UserEntity
import com.example.core.model.PointOfInterest
import com.example.core.model.User
import com.example.core.model.UserComment
import com.example.feature.chargers.data.api.ElectricChargerApiClient
import com.example.feature.chargers.data.mapper.PointOfInterestMapper
import com.example.feature.chargers.domain.ElectricChargerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultElectricChargerRepository @Inject constructor(
    private val electricChargerApiClient: ElectricChargerApiClient,
) : ElectricChargerRepository {

    private val _pointOfInterests = MutableStateFlow<List<PointOfInterest>>(emptyList())
    override val pointOfInterests = _pointOfInterests.asStateFlow()

    override suspend fun getNearestElectricChargers(currentLocation: Location): List<PointOfInterest> =
        withContext(Dispatchers.Default) {
            val pois = electricChargerApiClient.getNearestElectricChargers(currentLocation)
                .map(PointOfInterestMapper::map)
            _pointOfInterests.value = pois
            return@withContext pois
        }
}