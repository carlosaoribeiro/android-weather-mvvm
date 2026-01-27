package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel

@Composable
fun HourlyForecastItem(
    uiModel: HourlyForecastUiModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                color = Color.White.copy(alpha = 0.15f)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            text = uiModel.hour,
            color = Color.White
        )

        Text(
            text = uiModel.temperatureText,
            color = Color.White
        )

        Text(
            text = uiModel.description,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}
@Composable
fun HourlyForecastRow(
    items: List<HourlyForecastUiModel>
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        items.forEach { item ->
            HourlyForecastItem(
                uiModel = item
            )
        }
    }
}