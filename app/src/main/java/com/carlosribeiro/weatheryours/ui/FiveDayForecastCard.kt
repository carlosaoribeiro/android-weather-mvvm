package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.ui.model.DailyForecastUiModel
import com.carlosribeiro.weatheryours.ui.theme.DividerColor
import com.carlosribeiro.weatheryours.ui.theme.GlassBorder
import com.carlosribeiro.weatheryours.ui.theme.GlassSurface
import com.carlosribeiro.weatheryours.ui.theme.TextPrimary
import com.carlosribeiro.weatheryours.ui.theme.TextSecondary
import com.carlosribeiro.weatheryours.ui.theme.TextTertiary

/**
 * FiveDayForecastCard
 *
 * DailyForecastUiModel: day, icon (string), minTemp, maxTemp
 */
@Composable
fun FiveDayForecastCard(
    items   : List<DailyForecastUiModel>,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(20.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(GlassSurface)
            .border(1.dp, GlassBorder, shape)
            .padding(vertical = 8.dp)
    ) {
        items.forEachIndexed { index, item ->
            DailyRow(item = item)
            if (index < items.lastIndex) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(1.dp)
                        .background(DividerColor)
                )
            }
        }
    }
}

@Composable
private fun DailyRow(
    item    : DailyForecastUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier          = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Dia (ex: "Amanhã", "Sáb")
        Text(
            text     = item.day,
            style    = MaterialTheme.typography.bodyMedium.copy(
                color      = TextPrimary,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.weight(1.2f)
        )

        // Ícone + descrição do campo icon (ex: "sunny", "cloudy")
        Row(
            modifier          = Modifier.weight(2f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeatherIcon(
                description = item.icon,
                size        = 22.dp
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text  = item.icon.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
            )
        }

        // Máxima
        Text(
            text     = item.maxTemp,
            style    = MaterialTheme.typography.bodyMedium.copy(
                color      = TextPrimary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(0.8f)
        )

        // Mínima
        Text(
            text     = item.minTemp,
            style    = MaterialTheme.typography.bodyMedium.copy(color = TextTertiary),
            modifier = Modifier.weight(0.8f)
        )
    }
}