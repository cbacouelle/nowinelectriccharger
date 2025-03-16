package com.example.feature.location.data

import android.location.Location
import com.example.core.datastore.LocationDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultLocationDataRepository @Inject constructor(
    private val locationDatasource: LocationDatasource
): LocationDataRepository {

    override val location: Flow<Location> = locationDatasource.location.map { locationData ->
        val locationAndroid = Location("")
        locationAndroid.longitude = locationData.longitude
        locationAndroid.latitude = locationData.latitude
        locationAndroid
    }

    override suspend fun setLocation(longitude: Double, latitude: Double) {
        locationDatasource.setLocation(longitude = longitude, latitude = latitude)
    }

}