package com.example.feature.chargers.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ElectricChargerApiService {

    companion object {
        const val POI_BASE_URL = "api/v3/poi"
    }

    @GET(POI_BASE_URL)
    suspend fun getNearestElectricChargers(
        @Query("output") output: String = "json",
        @Query("maxresults") maxresults: Int = 20,
        @Query("distance") distance: Int = 10,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Header("X-API-KEY") apiKey: String = "6f9cfbad-115a-4894-b8f2-8ce665b3fc86",
    ): List<PointOfInterestDto>

}