package com.example.feature.chargers.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable data object ChargerListRoute // route to ChargerListScreen

@Serializable data object ChargerListBaseRoute // route to base navigation graph

fun NavGraphBuilder.chargerDestination(
    onChargerClick: () -> Unit,
    viewModel: ChargerViewModel,
//    chargerDetailDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<ChargerListBaseRoute>(
        startDestination = ChargerListRoute,
    ) {
        composable<ChargerListRoute>() {
            ChargerScreen(viewModel)
        }
//        chargerDetailDestination()
    }
}