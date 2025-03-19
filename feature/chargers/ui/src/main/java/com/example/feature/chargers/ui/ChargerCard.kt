package com.example.feature.chargers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.ui.conditional
import com.example.core.model.AddressInfo
import com.example.core.model.PointOfInterest
import com.example.core.model.StatusType

@Composable
fun ChargerCard(
    charger: PointOfInterest,
    onChargerCardClick: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .clickable(
                enabled = true,
                onClick = {
                    onChargerCardClick(charger.ID)
                }
            )
    ) {
        Row {
            if (charger.MediaItems.isNotEmpty()) {
                AsyncImage(
                    model = charger.MediaItems[0].ItemThumbnailURL,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    Text(
                        text = "${charger.AddressInfo.Title}",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(RoundedCornerShape(100))
                                .conditional(
                                    charger.StatusType == StatusType.OPERATIONAL, {
                                        background(Color(0xff90be6d))
                                    }
                                )
                                .conditional(
                                    charger.StatusType == StatusType.UNKNOWN, {
                                        background(Color(0xff577590))
                                    }
                                )
                                .conditional(
                                    charger.StatusType == StatusType.PLANNED_FOR_FUTURE, {
                                        background(Color(0xfff8961e))
                                    }
                                )
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_local_gas_station),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "${charger.AddressInfo.ReadableDistance}",
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                    }
                }
            }
        }
    }
}

var pointOfInterest = PointOfInterest(
    ID = 1234,
    UUID = "1234",
    IsRecentlyVerified = true,
    DateLastVerified = "yesterday",
    GeneralComments = "Fonctionne correctement",
    StatusType = StatusType.OPERATIONAL,
    DateLastStatusUpdate = "yesterday",
    UserComments = emptyList(),
    MediaItems = emptyList(),
    AddressInfo = AddressInfo(
        ID = 4567,
        AddressLine1 = "Place du marché",
        Latitude = -44.567,
        Longitude = 23.987,
        ReadableDistance = "3.4567",
        Distance = 3.4567,
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

//@Preview
//@Composable
//fun OperationalChargerCardPreview() {
//    ChargerCard(
//        pointOfInterest
//    )
//}
//
//
//@Preview
//@Composable
//fun UnknownChargerCardPreview() {
//    ChargerCard(
//        pointOfInterest.copy(
//            StatusType = StatusType.UNKNOWN
//        )
//    )
//}
//
//@Preview
//@Composable
//fun FutureChargerCardPreview() {
//    ChargerCard(
//        pointOfInterest.copy(
//            StatusType = StatusType.PLANNED_FOR_FUTURE
//        )
//    )
//}
