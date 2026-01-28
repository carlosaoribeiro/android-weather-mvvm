package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.ui.model.DailyForecastUiModel

@Composable
fun DailyForecastRow(
    model: DailyForecastUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = model.day,
            color = Color.White,
            modifier = Modifier.width(48.dp)
        )

        Text(
            text = when (model.icon) {
                "sunny" -> "☀️"
                "cloudy" -> "☁️"
                "rain" -> "🌧️"
                else -> "⛅"
            },
            modifier = Modifier.width(32.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = model.minTemp,
            color = Color.White.copy(alpha = 0.6f),
            modifier = Modifier.width(32.dp),
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = model.maxTemp,
            color = Color.White,
            modifier = Modifier.width(32.dp),
            textAlign = TextAlign.End
        )
    }
}
