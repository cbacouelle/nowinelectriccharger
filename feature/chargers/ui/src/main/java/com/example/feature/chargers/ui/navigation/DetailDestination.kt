package com.example.feature.chargers.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feature.chargers.ui.DetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(val poiId: Int)

fun NavController.navigateToDetail(
    poiId: Int,
) {
    navigate(route = DetailRoute(poiId))
}

fun NavGraphBuilder.detailScreen(
    showBackButton: Boolean,
    onBackButtonClick: () -> Unit,
) {
    composable<DetailRoute>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(250)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(250)
            )
        }

    ) {
        DetailScreen(
            showBackButton,
            onBackButtonClick,
        )
    }
}