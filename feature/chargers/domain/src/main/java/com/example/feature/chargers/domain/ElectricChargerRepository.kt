package com.example.feature.chargers.domain

import android.location.Location
import com.example.feature.chargers.domain.model.PointOfInterest

interface ElectricChargerRepository {

    suspend fun getNearestElectricChargers(currentLocation: Location): List<PointOfInterest>

}