package com.example.feature.chargers.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.feature.chargers.domain.ElectricChargerRepository
import com.example.core.model.PointOfInterest
import com.example.feature.chargers.ui.navigation.DetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: ElectricChargerRepository
) : ViewModel() {

    private val poiId = savedStateHandle.toRoute<DetailRoute>().poiId

    @OptIn(ExperimentalCoroutinesApi::class)
    val detailViewState = repository.pointOfInterests.mapLatest { pointOfInterests ->
        val poi = pointOfInterests.firstOrNull { pointOfInterest ->
            pointOfInterest.ID == poiId
        }
        if (poi == null) {
            DetailViewState.Error(errorMessage = "No point of interest found for the given ID")
        } else {
            DetailViewState.Success(poi = poi)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DetailViewState.Loading,
    )
}

sealed interface DetailViewState {
    data object Loading : DetailViewState
    data class Success(val poi: PointOfInterest) : DetailViewState
    data class Error(val errorMessage: String) : DetailViewState
}