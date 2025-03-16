package com.example.feature.location.domain

import android.location.Location
import com.example.feature.location.data.LocationDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetLocationUseCase @Inject constructor(
    private val locationDataRepository: LocationDataRepository
) {

    operator fun invoke(currentLocation: Location) =
        runBlocking {
            withContext(Dispatchers.Default) {
                locationDataRepository.setLocation(
                    longitude = currentLocation.longitude,
                    latitude = currentLocation.latitude
                )
            }
        }
}