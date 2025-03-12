package com.example.nowinelectriccharger

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feature.chargers.ui.ChargerViewModel

@Composable
fun NowInElectricChargerApp(
    appState: NowInElectricChargerAppState,
    viewModel: ChargerViewModel,
    modifier: Modifier = Modifier,
) {
    NowInElectricChargerHost(
        appState,
        viewModel,
        modifier
    )
}
