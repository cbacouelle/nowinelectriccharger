package com.example.feature.chargers.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChargerScreen(
    modifier: Modifier,
    viewModel: ChargerViewModel = hiltViewModel()
) {
    val electricChargersViewState by viewModel.electricChargersViewState.collectAsState(ChargerViewState.Loading)

    ChargerScreenComponent(modifier, electricChargersViewState)
}

@Composable
fun ChargerScreenComponent(
    modifier: Modifier,
    viewState: ChargerViewState
) {
    val title: String = stringResource(R.string.charger_screen_title)

    Box(modifier) {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp, start = 16.dp,)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(end = 12.dp)
                )
            }

            ChargerList(
                viewState = viewState
            )
        }
    }
}


fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(animation = tween(1000)),
    )
    val colors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )
//    val shimmerAnimation = transition.animateFloat(
//        initialValue = 0f,
//        targetValue = 1000f,
//        animationSpec = infiniteRepeatable(animation = tween(1000, easing = LinearEasing)),
//        label = "shimmer"
//    )
    background(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(startOffsetX, 0f),
            end = Offset(x = startOffsetX + size.width.toFloat(), y =  size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}