package com.example.feature.chargers.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.isLocationPermissionNotGranted
import com.example.feature.chargers.domain.GetElectricChargersUseCase
import com.example.feature.chargers.domain.model.PointOfInterest
import com.example.feature.location.domain.GetLocationUseCase
import com.example.feature.location.domain.SetLocationUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChargerViewModel @Inject constructor(
    private val getElectricChargersUseCase: GetElectricChargersUseCase,
    getLocationUseCase: GetLocationUseCase,
    private val setLocationUseCase: SetLocationUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val currentLocation = getLocationUseCase()

    init {
        checkLocationPermission()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val electricChargersViewState: StateFlow<ChargerViewState> =
        currentLocation.filterNotNull().mapLatest { currentLocation ->
            getElectricChargersUseCase(currentLocation)
        }.map {
            ChargerViewState.Success(it)
        }.catch { error ->
            println(error)
            ChargerViewState.Error("An error occurred while retrieving the chargers")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChargerViewState.Loading,
        )

    private fun checkLocationPermission() {
        if (!isLocationPermissionNotGranted(context)) {
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val cancellationTokenSource = CancellationTokenSource()

        fusedLocationClient.getCurrentLocation(100, cancellationTokenSource.token)
            .addOnSuccessListener { location ->
                this.setLocationUseCase(location)
            }
            .addOnFailureListener { exception ->
                println("Location Oops location failed with exception: $exception")
            }
    }

}

sealed interface ChargerViewState {
    data object Loading : ChargerViewState
    data class Success(val chargers: List<PointOfInterest>) : ChargerViewState
    data class Error(val errorMessage: String) : ChargerViewState
}