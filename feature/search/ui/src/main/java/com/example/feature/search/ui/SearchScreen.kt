package com.example.feature.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val location by viewModel.currentLocation.collectAsState(null)

    Column(
        modifier
    ) {
        Text(
            text = "Here is the longitude: ${location?.longitude.toString()}"
        )
        Text(
            text = "Here is the latitude: ${location?.latitude.toString()}"
        )
    }
}