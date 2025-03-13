package com.example.feature.chargers.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.shimmerEffect
import com.example.feature.chargers.domain.model.AddressInfo
import com.example.feature.chargers.domain.model.PointOfInterest
import com.example.feature.chargers.domain.model.StatusType
import com.example.theme.NowinelectricchargerTheme

@Composable
fun ChargerList(
    viewState: ChargerViewState,
    onChargerCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollableState = rememberLazyListState()

    when (viewState) {
        is ChargerViewState.Loading ->
            LazyColumn(
                state = scrollableState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = modifier
            ) {
                items(10) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(bottom = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .shimmerEffect()
                    )
                }
            }

        is ChargerViewState.Success ->
            LazyColumn(
                modifier = modifier,
                state = scrollableState,
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(viewState.chargers) { charger ->
                    ChargerCard(
                        charger = charger,
                        onChargerCardClick = onChargerCardClick
                    )
                }
            }

        is ChargerViewState.Error ->
            Text(
                text = "An error occurred while retrieving charger"
            )
    }

}

@Preview
@Composable
private fun ChargerListLoadingPreview() {
    val viewState = ChargerViewState.Loading
    NowinelectricchargerTheme {
        ChargerList(
            viewState = viewState,
            onChargerCardClick = { }
        )
    }
}

@Preview
@Composable
private fun ChargerListSuccessPreview() {
    val chargers = arrayListOf(
        PointOfInterest(
            ID = 1234,
            UUID = "1234",
            IsRecentlyVerified = true,
            DateLastVerified = "yesterday",
            GeneralComments = "Fonctionne correctement",
            StatusType = StatusType.UNKNOWN,
            DateLastStatusUpdate = "yesterday",
            UserComments = emptyList(),
            MediaItems = emptyList(),
            AddressInfo = AddressInfo(
                ID = 4567,
                AddressLine1 = "Place du marché",
                Latitude = -44.567,
                Longitude = 23.987,
                Distance = "3.4567",
                Title = "Place du marché",
                AddressLine2 = "",
                Town = "",
                StateOrProvince = "",
                Postcode = "",
                CountryID = 0,
                ContactTelephone1 = "",
                ContactTelephone2 = "",
                ContactEmail = "",
                AccessComments = "",
                RelatedURL = ""
            ),
            UsageCost = "",
            NumberOfPoints = 2
        )
    )
    val viewState = ChargerViewState.Success(chargers)
    NowinelectricchargerTheme {
        ChargerList(
            viewState = viewState,
            onChargerCardClick = { }
        )
    }
}