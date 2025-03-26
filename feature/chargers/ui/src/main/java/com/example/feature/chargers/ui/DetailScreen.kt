package com.example.feature.chargers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.theme.NowInElectricChargerTheme
import com.google.gson.Gson

@Composable
fun DetailScreen(
    onBackButtonClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val viewState: DetailViewState by viewModel.detailViewState.collectAsStateWithLifecycle()

    DetailScreenComponent(
        viewState = viewState,
        onBackButtonClick = onBackButtonClick,
        onFavoriteButtonClick = onFavoriteButtonClick,
        onGoToLocationButtonClick = viewModel::handleGoToLocation
    )
}

@Composable
fun DetailScreenComponent(
    onBackButtonClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    onGoToLocationButtonClick: (Double, Double) -> Unit,
    viewState: DetailViewState,
    modifier: Modifier = Modifier,
) {

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
            println("=====")
            val json = Gson().toJson(viewState.poi)
            println(json)
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.2f)
                            .background(colorResource(R.color.stone_30))
                            .padding(top = 24.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                IconButton(
                                    onClick = onBackButtonClick,
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                        contentDescription = stringResource(R.string.detail_screen_navigation_back),
                                        tint = colorResource(R.color.stone_80)
                                    )
                                }
                                IconButton(
                                    onClick = onFavoriteButtonClick,
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Star,
                                        contentDescription = stringResource(R.string.detail_screen_navigation_back),
                                        tint = colorResource(R.color.stone_80),
                                    )
                                }
                            }

                            Text(
                                text = viewState.poi.AddressInfo.Title.toString(),
                                color = colorResource(R.color.stone_80),
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .padding(top = 12.dp),
                            )
                            Text(
                                text = viewState.poi.AddressInfo.Town.toString(),
                                color = colorResource(R.color.stone_70),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            )
                        }
                    }

                    AddressInfoCard(
                        addressInfo = viewState.poi.AddressInfo,
                        onGoToLocationButtonClick = onGoToLocationButtonClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .offset(y = (-24).dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    NowInElectricChargerTheme {
        DetailScreenComponent(
            onBackButtonClick = {},
            onFavoriteButtonClick = {},
            onGoToLocationButtonClick = { latitude, longitude -> },
            viewState = DetailViewState.Success(poi = pointOfInterest),
        )
    }
}