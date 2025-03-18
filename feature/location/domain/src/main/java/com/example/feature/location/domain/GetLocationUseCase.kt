package com.example.feature.location.domain

import android.location.Location
import com.example.feature.location.data.LocationDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationDataRepository: LocationDataRepository
) {

    operator fun invoke(): Flow<Location> =
        locationDataRepository.location
}