package com.example.feature.location.data

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationDataRepository {

    val location: Flow<Location>

    suspend fun setLocation(longitude: Double, latitude: Double)

}