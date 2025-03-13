package com.example.feature.chargers.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChargersScreen(
    onChargerCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChargerViewModel,
) {
    val viewState by viewModel.electricChargersViewState.collectAsStateWithLifecycle(
        ChargerViewState.Loading
    )

    ChargersScreenComponent(
        modifier = modifier,
        viewState = viewState,
        onChargerCardClick = onChargerCardClick
    )
}

@Composable
fun ChargersScreenComponent(
    modifier: Modifier,
    viewState: ChargerViewState,
    onChargerCardClick: (Int) -> Unit,
) {
    val title: String = stringResource(R.string.charger_screen_title)
    Box(modifier) {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp, start = 16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(end = 12.dp)
                )
            }

            ChargerList(
                viewState = viewState,
                onChargerCardClick = onChargerCardClick,
            )
        }
    }
}