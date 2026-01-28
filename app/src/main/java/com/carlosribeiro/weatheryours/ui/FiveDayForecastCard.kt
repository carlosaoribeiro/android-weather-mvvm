package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.ui.model.DailyForecastUiModel

@Composable
fun FiveDayForecastCard(
    items: List<DailyForecastUiModel>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2E3A67)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "5-DAY FORECAST",
                color = Color.White.copy(alpha = 0.7f),
                style = MaterialTheme.typography.labelMedium
            )

            items.forEach { day ->
                ForecastRow(day)
            }
        }
    }
}

@Composable
private fun ForecastRow(model: DailyForecastUiModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = model.day,
            color = Color.White,
            modifier = Modifier.width(48.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Placeholder do ícone (depois vira Image/Icon)
        Text(
            text = "☀️",
            modifier = Modifier.width(32.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = model.minTemp,
            color = Color.White.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = model.maxTemp,
            color = Color.White
        )
    }
}
@Preview(showBackground = true)
@Composable
fun FiveDayForecastCardPreview() {
    FiveDayForecastCard(
        items = listOf(
            DailyForecastUiModel("Today", "sunny", "-1°", "13°"),
            DailyForecastUiModel("Thu", "cloudy", "1°", "18°"),
            DailyForecastUiModel("Fri", "cloudy", "2°", "11°"),
            DailyForecastUiModel("Sat", "sunny", "-1°", "8°"),
            DailyForecastUiModel("Sun", "sunny", "-4°", "12°")
        )
    )
}