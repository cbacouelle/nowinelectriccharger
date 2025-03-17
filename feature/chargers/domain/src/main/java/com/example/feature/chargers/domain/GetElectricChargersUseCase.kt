package com.example.feature.chargers.domain

import android.location.Location
import com.example.core.model.PointOfInterest
import javax.inject.Inject

class GetElectricChargersUseCase @Inject constructor(
    private val electricChargerRepository: ElectricChargerRepository,
) {

    suspend operator fun invoke(currentLocation: Location): List<PointOfInterest> =
        electricChargerRepository.getNearestElectricChargers(currentLocation)
}