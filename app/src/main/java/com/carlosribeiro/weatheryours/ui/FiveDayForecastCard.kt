package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            // HEADER
            Text(
                text = "5-DAY FORECAST",
                color = Color.White.copy(alpha = 0.7f),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium
            )

            // DIVIDER (header)
            Divider(
                color = Color.White.copy(alpha = 0.15f),
                thickness = 1.dp
            )

            // ROWS
            items.forEachIndexed { index, day ->
                ForecastRow(day)

                if (index < items.lastIndex) {
                    Divider(
                        color = Color.White.copy(alpha = 0.08f),
                        thickness = 0.5.dp
                    )
                }
            }
        }
    }
}

@Composable
private fun ForecastRow(model: DailyForecastUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Day
        Text(
            text = model.day,
            color = Color.White,
            modifier = Modifier.width(56.dp),
            fontWeight = FontWeight.Medium
        )

        // Icon (placeholder – pronto pra trocar por Image/Icon)
        Text(
            text = "☀️",
            modifier = Modifier.width(32.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // MIN
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "MIN",
                fontSize = 10.sp,
                color = Color.White.copy(alpha = 0.6f),
                letterSpacing = 0.6.sp
            )
            Text(
                text = model.minTemp,
                color = Color.White.copy(alpha = 0.75f),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        // MAX
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "MAX",
                fontSize = 10.sp,
                color = Color.White.copy(alpha = 0.6f),
                letterSpacing = 0.6.sp
            )
            Text(
                text = model.maxTemp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
