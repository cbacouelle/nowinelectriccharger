package com.example.feature.chargers.data.api

import android.location.Location
import retrofit2.Retrofit
import javax.inject.Inject

class ElectricChargerApiClient @Inject constructor(
    retrofit: Retrofit,
) {

    private val electricChargerApiService = retrofit.create(ElectricChargerApiService::class.java)

    suspend fun getNearestElectricChargers(currentLocation: Location): List<PointOfInterestDto> =
        electricChargerApiService.getNearestElectricChargers(
            latitude = currentLocation.latitude,
            longitude = currentLocation.longitude
        )

}