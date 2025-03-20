package com.example.feature.chargers.domain

import android.location.Location
import com.example.core.model.PointOfInterest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetElectricChargersUseCase @Inject constructor(
    private val electricChargerRepository: ElectricChargerRepository,
) {

    suspend operator fun invoke(currentLocation: Location): Flow<List<PointOfInterest>> =
        withContext(
            Dispatchers.Default
        ) {
            electricChargerRepository.getPointOfInterests(currentLocation)
        }

}