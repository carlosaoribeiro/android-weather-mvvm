package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Umbrella
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosribeiro.weatheryours.R
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel
import com.carlosribeiro.weatheryours.ui.theme.AccentIce
import com.carlosribeiro.weatheryours.ui.theme.GlassBorder
import com.carlosribeiro.weatheryours.ui.theme.GlassBorderSubtle
import com.carlosribeiro.weatheryours.ui.theme.GlassSurface
import com.carlosribeiro.weatheryours.ui.theme.TextPrimary
import com.carlosribeiro.weatheryours.ui.theme.TextSecondary
import com.carlosribeiro.weatheryours.ui.theme.TextTertiary

@Composable
fun WeatherMetricsCard(
    weather : WeatherUiModel,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(20.dp)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(GlassSurface)
            .border(1.dp, GlassBorder, shape)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        MetricItem(
            icon  = Icons.Filled.WaterDrop,
            label = stringResource(R.string.humidity),
            value = weather.humidityText,
            tint  = AccentIce
        )

        GlassDivider()

        MetricItem(
            icon  = Icons.Filled.Air,
            label = stringResource(R.string.wind),
            value = weather.windSpeedText,
            tint  = TextSecondary
        )

        GlassDivider()

        MetricItem(
            icon  = Icons.Filled.Umbrella,
            label = stringResource(R.string.rain),
            value = weather.rainChanceText,
            tint  = AccentIce
        )
    }
}

@Composable
private fun MetricItem(
    icon    : ImageVector,
    label   : String,
    value   : String,
    tint    : Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier            = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector        = icon,
            contentDescription = label,
            tint               = tint,
            modifier           = Modifier.size(22.dp)
        )
        Text(
            text  = label,
            style = MaterialTheme.typography.labelMedium.copy(
                color         = TextTertiary,
                letterSpacing = 1.sp,
                fontWeight    = FontWeight.Medium
            )
        )
        Text(
            text  = value,
            style = MaterialTheme.typography.titleSmall.copy(
                color      = TextPrimary,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
private fun GlassDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(48.dp)
            .background(GlassBorderSubtle)
    )
}