package com.example.feature.chargers.data.api

import android.location.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class ElectricChargerApiClient @Inject constructor(
    retrofit: Retrofit,
) {

    private val electricChargerApiService = retrofit.create(ElectricChargerApiService::class.java)

    suspend fun getNearestElectricChargers(currentLocation: Location): List<PointOfInterestDto> = withContext(Dispatchers.IO) {
        electricChargerApiService.getNearestElectricChargers(
            latitude = currentLocation.latitude,
            longitude = currentLocation.longitude
        )
    }

}