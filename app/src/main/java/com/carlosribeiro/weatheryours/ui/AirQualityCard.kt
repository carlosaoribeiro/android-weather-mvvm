package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosribeiro.weatheryours.R
import com.carlosribeiro.weatheryours.ui.model.AirQualityUiModel

@Composable
fun AirQualityCard(model: AirQualityUiModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2E355E)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = stringResource(R.string.air_quality),
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            Divider(
                color = Color.White.copy(alpha = 0.15f),
                thickness = 1.dp
            )

            Text(
                text = model.index.toString(),
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = when (model.level) {
                    "GOOD" -> stringResource(R.string.air_quality_good)
                    "FAIR" -> stringResource(R.string.air_quality_fair)
                    "POOR" -> stringResource(R.string.air_quality_poor)
                    else -> ""
                },
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = stringResource(R.string.air_quality_description, model.index),
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 14.sp
            )
        }
    }
}
