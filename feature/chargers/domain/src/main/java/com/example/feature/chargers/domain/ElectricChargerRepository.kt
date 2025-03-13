package com.example.feature.chargers.domain

import android.location.Location
import com.example.feature.chargers.domain.model.PointOfInterest
import kotlinx.coroutines.flow.Flow

interface ElectricChargerRepository {

    val pointOfInterests: Flow<List<PointOfInterest>>

    suspend fun getNearestElectricChargers(currentLocation: Location): List<PointOfInterest>

}