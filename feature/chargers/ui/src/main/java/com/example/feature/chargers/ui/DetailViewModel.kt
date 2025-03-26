package com.example.feature.chargers.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.feature.chargers.domain.ElectricChargerRepository
import com.example.core.model.PointOfInterest
import com.example.feature.chargers.ui.navigation.DetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: ElectricChargerRepository,
    @ApplicationContext val context: Context,
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

    fun handleGoToLocation(longitude: Double, latitude: Double) {
        val zoom = 21
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?zoom=$zoom")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mapIntent.setPackage("com.google.android.apps.maps")
        context.startActivity(mapIntent)
    }
}

sealed interface DetailViewState {
    data object Loading : DetailViewState
    data class Success(val poi: PointOfInterest) : DetailViewState
    data class Error(val errorMessage: String) : DetailViewState
}