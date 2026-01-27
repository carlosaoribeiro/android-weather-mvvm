package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel

@Composable
fun HourlyForecastRow(
    items: List<HourlyForecastUiModel>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            HourlyForecastItem(item)
        }
    }
}

@Composable
private fun HourlyForecastItem(
    model: HourlyForecastUiModel
) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "${model.hour} • ${model.temperatureText} • ${model.description}",
            color = Color.White
        )
    }
}
