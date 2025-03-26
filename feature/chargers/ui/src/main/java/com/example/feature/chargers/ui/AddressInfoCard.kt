package com.example.feature.chargers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.model.AddressInfo
import com.example.theme.NowInElectricChargerTheme

@Composable
fun AddressInfoCard(
    addressInfo: AddressInfo, modifier: Modifier = Modifier,
    onGoToLocationButtonClick: (Double, Double) -> Unit
) {

    val leftSize = 0.18f

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(R.color.stone_40))
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Box(
            contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { onGoToLocationButtonClick(addressInfo.Longitude, addressInfo.Latitude) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_assistant_direction),
                    contentDescription = stringResource(R.string.detail_screen_navigation_go_to_map),
                    tint = colorResource(R.color.stone_80),
                )
            }
        }
        Column {
            Text(
                text = stringResource(R.string.address_info_card_title), style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    letterSpacing = 0.5.sp,
                ), modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.address_info_address_line), style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        letterSpacing = 0.5.sp
                    ), modifier = Modifier.fillMaxWidth(leftSize)
                )
                Text(
                    text = addressInfo.AddressLine1.toString(), style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        letterSpacing = 0.5.sp
                    )
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            if (addressInfo.AddressLine2.isNullOrEmpty().not()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth(leftSize))
                    Text(
                        text = addressInfo.AddressLine2.toString(), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }

            if (addressInfo.Postcode.isNullOrEmpty().not()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.address_info_post_code), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        ), modifier = Modifier.fillMaxWidth(leftSize)
                    )
                    Text(
                        text = addressInfo.Postcode.toString(), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }


            if (addressInfo.ContactEmail.isNullOrEmpty().not()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.address_info_contact_email),
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        ),
                        modifier = Modifier.fillMaxWidth(leftSize)
                    )
                    Text(
                        text = addressInfo.ContactEmail.toString(), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }

            if (addressInfo.ContactTelephone1.isNullOrEmpty().not()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.address_info_telephone), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        ), modifier = Modifier.fillMaxWidth(leftSize)
                    )
                    Text(
                        text = addressInfo.ContactTelephone1.toString(), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }

            if (addressInfo.ContactTelephone2.isNullOrEmpty().not()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth(leftSize))
                    Text(
                        text = addressInfo.ContactTelephone2.toString(), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }

            if (addressInfo.AccessComments.isNullOrEmpty().not()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.address_info_comments), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        ), modifier = Modifier.fillMaxWidth(leftSize)
                    )
                    Text(
                        text = addressInfo.AccessComments.toString(), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }

            if (addressInfo.StateOrProvince.isNullOrEmpty().not()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.address_info_state), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        ), modifier = Modifier.fillMaxWidth(leftSize)
                    )
                    Text(
                        text = addressInfo.StateOrProvince.toString(), style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.address_info_latitude), style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        letterSpacing = 0.5.sp
                    ), modifier = Modifier.fillMaxWidth(leftSize)
                )
                Text(
                    text = addressInfo.Latitude.toString(), style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        letterSpacing = 0.5.sp
                    )
                )
            }
            Spacer(modifier = Modifier.size(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.address_info_longitude), style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        letterSpacing = 0.5.sp
                    ), modifier = Modifier.fillMaxWidth(leftSize)
                )
                Text(
                    text = addressInfo.Longitude.toString(), style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        letterSpacing = 0.5.sp
                    )
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
        }
    }

}

@Preview
@Composable
fun AddressInfoCardPreview() {
    NowInElectricChargerTheme {
        AddressInfoCard(
            addressInfo = AddressInfo(
                ID = 456,
                AddressLine1 = "3 place de l'Église",
                AddressLine2 = "",
                Town = "Bordeaux",
                StateOrProvince = "Gironde",
                Postcode = "33000",
                CountryID = 33,
                Latitude = 23.3456,
                Longitude = -12.4534,
                ContactTelephone1 = "+336766767",
                ContactTelephone2 = "",
                ContactEmail = "administration@gouv.fr",
                AccessComments = "Ne réponds pas vite mais ca va",
                RelatedURL = "",
                Distance = 3.45,
                ReadableDistance = "3.5km",
                Title = "Place de l'église, là où il y a le marché"
            ),
            onGoToLocationButtonClick = { latitude, longitude -> },
        )
    }
}
