package com.example.nowinelectriccharger

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.feature.chargers.ui.ChargerListBaseRoute
import com.example.feature.chargers.ui.ChargerViewModel
import com.example.feature.chargers.ui.chargerDestination

@Composable
fun NowInElectricChargerHost(
    appState: NowInElectricChargerAppState,
    viewModel: ChargerViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = appState.navController,
        startDestination = ChargerListBaseRoute,
        modifier = modifier
    ) {
        chargerDestination(
            onChargerClick = { },
            viewModel
        )
    }
}
