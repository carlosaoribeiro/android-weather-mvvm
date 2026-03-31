package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel
import com.carlosribeiro.weatheryours.ui.theme.AccentIce
import com.carlosribeiro.weatheryours.ui.theme.GlassBorderSubtle
import com.carlosribeiro.weatheryours.ui.theme.GlassSurface
import com.carlosribeiro.weatheryours.ui.theme.GlassSurfaceStrong
import com.carlosribeiro.weatheryours.ui.theme.TextPrimary
import com.carlosribeiro.weatheryours.ui.theme.TextSecondary

/**
 * HourlyForecastRow
 *
 * Cards distribuídos com weight(1f) — preenchem toda a largura
 * disponível independente do tamanho de tela, sem scroll horizontal.
 * O primeiro item é destacado como "agora".
 */
@Composable
fun HourlyForecastRow(
    items   : List<HourlyForecastUiModel>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEachIndexed { index, item ->
            HourlyItem(
                item     = item,
                isNow    = index == 0,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun HourlyItem(
    item    : HourlyForecastUiModel,
    isNow   : Boolean,
    modifier: Modifier = Modifier
) {
    val shape       = RoundedCornerShape(16.dp)
    val bgColor     = if (isNow) GlassSurfaceStrong else GlassSurface
    val borderColor = if (isNow) AccentIce.copy(alpha = 0.5f) else GlassBorderSubtle

    Column(
        modifier = modifier
            .clip(shape)
            .background(bgColor)
            .border(1.dp, borderColor, shape)
            .padding(vertical = 14.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text  = item.hour,
            style = MaterialTheme.typography.bodySmall.copy(
                color      = if (isNow) TextPrimary else TextSecondary,
                fontWeight = if (isNow) FontWeight.SemiBold else FontWeight.Normal
            )
        )

        WeatherIcon(
            description = item.description,
            size        = 26.dp
        )

        Text(
            text  = item.temperatureText,
            style = MaterialTheme.typography.bodyMedium.copy(
                color      = TextPrimary,
                fontWeight = FontWeight.Bold
            )
        )
    }
}