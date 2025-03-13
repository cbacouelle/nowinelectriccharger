package com.example.feature.chargers.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailScreen(
    showBackButton: Boolean,
    onBackButtonClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val viewState: DetailViewState by viewModel.detailViewState.collectAsStateWithLifecycle()

    DetailScreenComponent(
        showBackButton = showBackButton,
        viewState = viewState,
        onBackButtonClick = onBackButtonClick,
    )
}

@Composable
fun DetailScreenComponent(
    showBackButton: Boolean,
    onBackButtonClick: () -> Unit,
    viewState: DetailViewState,
    modifier: Modifier = Modifier,
) {

    val goBackContentDescription: String = stringResource(R.string.detail_screen_navigation_back)
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBackButton) {
                IconButton(
                    onClick = onBackButtonClick,
                    modifier = modifier,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = goBackContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            when (viewState) {
                is DetailViewState.Loading -> {
                    Text(
                        text = "This is loading"
                    )
                }

                is DetailViewState.Error -> {
                    Text(
                        text = "This is in error"
                    )
                }

                is DetailViewState.Success -> {
                    Text(
                        text = viewState.poi.AddressInfo.Title.toString()
                    )
                }
            }
        }
    }
}