package com.example.feature.chargers.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.feature.chargers.ui.ChargersScreen
import com.example.feature.chargers.ui.ChargerViewModel
import kotlinx.serialization.Serializable

@Serializable
data object ChargerListRoute // route to ChargerListScreen

@Serializable
data object ChargerListBaseRoute // route to base navigation graph

fun NavGraphBuilder.chargerDestination(
    onChargerCardClick: (Int) -> Unit,
    viewModel: ChargerViewModel,
    chargerDetailDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<ChargerListBaseRoute>(
        startDestination = ChargerListRoute,
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(250)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(250)
            )
        }
    ) {
        composable<ChargerListRoute>() {
            ChargersScreen(
                viewModel = viewModel,
                onChargerCardClick = onChargerCardClick
            )
        }
        chargerDetailDestination()
    }
}