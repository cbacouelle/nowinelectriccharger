package com.example.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.core.datastore_proto.Location
import com.example.core.datastore_proto.copy
import com.example.core.model.LocationData
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class LocationDatasource @Inject constructor(
    private val locationDataStore: DataStore<Location>,
)  {

    val location = locationDataStore.data
        .map {
            LocationData(
                latitude = it.latitude,
                longitude = it.longitude,
            )
        }

    suspend fun setLocation(latitude: Double, longitude: Double) {
        try {
            locationDataStore.updateData { location ->
                location.copy {
                    this.latitude = latitude
                    this.longitude = longitude
                }
            }
        } catch (ioException: IOException) {
            Log.e("Locations", "Failed to update user location", ioException)
        }
    }
}