package com.example.feature.chargers.data.repository

import android.location.Location
import com.example.feature.chargers.data.api.ElectricChargerApiClient
import com.example.feature.chargers.data.mapper.PointOfInterestMapper
import com.example.feature.chargers.domain.ElectricChargerRepository
import com.example.feature.chargers.domain.model.PointOfInterest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultElectricChargerRepository @Inject constructor(
    private val electricChargerApiClient: ElectricChargerApiClient,
) : ElectricChargerRepository {

    override suspend fun getNearestElectricChargers(currentLocation: Location): List<PointOfInterest> = withContext(Dispatchers.Default) {
        electricChargerApiClient.getNearestElectricChargers(currentLocation).map(PointOfInterestMapper::map)
    }

}