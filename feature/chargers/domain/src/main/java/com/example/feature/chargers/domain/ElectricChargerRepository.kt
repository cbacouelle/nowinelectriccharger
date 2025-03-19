package com.example.feature.chargers.domain

import android.location.Location
import com.example.core.model.PointOfInterest
import com.example.core.model.User
import com.example.core.model.UserComment
import kotlinx.coroutines.flow.Flow

interface ElectricChargerRepository {

    val pointOfInterests: Flow<List<PointOfInterest>>

    suspend fun getNearestElectricChargers(currentLocation: Location): List<PointOfInterest>

}