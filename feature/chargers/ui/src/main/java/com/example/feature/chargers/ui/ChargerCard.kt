package com.example.feature.chargers.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.feature.chargers.domain.model.PointOfInterest

@Composable
fun ChargerCard(
    charger: PointOfInterest,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
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
                        Icon(
                            painter = painterResource(R.drawable.ic_local_gas_station),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "${charger.AddressInfo.Distance}",
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }
            }
        }
    }
}
