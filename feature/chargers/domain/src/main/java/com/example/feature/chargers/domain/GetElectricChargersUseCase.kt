package com.example.feature.chargers.domain

import android.location.Location
import com.example.core.model.PointOfInterest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetElectricChargersUseCase @Inject constructor(
    private val electricChargerRepository: ElectricChargerRepository,
) {

    suspend operator fun invoke(currentLocation: Location): Flow<List<PointOfInterest>> =
        electricChargerRepository.getPointOfInterests(currentLocation)
}