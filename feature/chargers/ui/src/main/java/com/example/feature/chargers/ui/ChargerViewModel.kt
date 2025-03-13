package com.example.feature.chargers.ui

import android.location.Location
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.chargers.domain.GetElectricChargersUseCase
import com.example.feature.chargers.domain.model.PointOfInterest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChargerViewModel @Inject constructor(
    private val getElectricChargersUseCase: GetElectricChargersUseCase,
) : ViewModel() {

    private val _currentLocation = MutableStateFlow<Location?>(null)
    private val currentLocation = _currentLocation.asStateFlow()

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

    fun setCurrentLocation(location: Location) {
        _currentLocation.value = location;
    }

}

sealed interface ChargerViewState {
    data object Loading : ChargerViewState
    data class Success(val chargers: List<PointOfInterest>) : ChargerViewState
    data class Error(val errorMessage: String) : ChargerViewState
}