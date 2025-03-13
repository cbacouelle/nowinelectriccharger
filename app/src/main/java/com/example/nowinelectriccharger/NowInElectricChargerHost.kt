package com.example.nowinelectriccharger

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.feature.chargers.ui.navigation.ChargerListBaseRoute
import com.example.feature.chargers.ui.ChargerViewModel
import com.example.feature.chargers.ui.navigation.chargerDestination
import com.example.feature.chargers.ui.navigation.detailScreen
import com.example.feature.chargers.ui.navigation.navigateToDetail

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
            onChargerCardClick = appState.navController::navigateToDetail,
            viewModel
        ) {
            detailScreen(
                showBackButton = true,
                onBackButtonClick = appState.navController::popBackStack,
            )
        }
    }
}
