package com.carlosribeiro.weatheryours.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Umbrella
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.ui.theme.ColorCloudy
import com.carlosribeiro.weatheryours.ui.theme.ColorFoggy
import com.carlosribeiro.weatheryours.ui.theme.ColorRainy
import com.carlosribeiro.weatheryours.ui.theme.ColorSnowy
import com.carlosribeiro.weatheryours.ui.theme.ColorSunny
import com.carlosribeiro.weatheryours.ui.theme.TextSecondary

@Composable
fun WeatherIcon(
    description: String,
    size: Dp = 64.dp,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "weather-icon")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue  = 1.05f,
        animationSpec = infiniteRepeatable(
            animation  = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "icon-scale"
    )

    val (icon, tint) = resolveIcon(description)

    Icon(
        imageVector        = icon,
        contentDescription = description,
        tint               = tint,
        modifier           = modifier
            .scale(scale)
            .size(size)
    )
}

private data class IconData(val icon: ImageVector, val tint: Color)

private fun resolveIcon(description: String): IconData {
    val d = description.lowercase()
    return when {
        d.contains("ensolarado") || d.contains("sol") ||
                d.contains("clear")      || d.contains("sunny")
            -> IconData(Icons.Filled.WbSunny, ColorSunny)

        d.contains("parcialmente") || d.contains("partly") || d.contains("scattered")
            -> IconData(Icons.Outlined.WbCloudy, ColorCloudy)

        d.contains("nublado") || d.contains("cloud") || d.contains("overcast")
            -> IconData(Icons.Filled.Cloud, ColorCloudy)

        d.contains("chuva") || d.contains("rain") ||
                d.contains("shower") || d.contains("drizzle")
            -> IconData(Icons.Filled.Umbrella, ColorRainy)

        d.contains("trovoada") || d.contains("thunder") ||
                d.contains("storm")    || d.contains("lightning")
            -> IconData(Icons.Filled.Bolt, Color(0xFFFFD54F))

        d.contains("neve") || d.contains("snow") ||
                d.contains("sleet") || d.contains("hail")
            -> IconData(Icons.Filled.AcUnit, ColorSnowy)

        d.contains("névoa")   || d.contains("neblina") ||
                d.contains("fog")     || d.contains("mist") || d.contains("haze")
            -> IconData(Icons.Filled.Cloud, ColorFoggy)

        d.contains("vento") || d.contains("windy") || d.contains("breezy")
            -> IconData(Icons.Filled.Air, TextSecondary)

        d.contains("noite") || d.contains("night") || d.contains("moon")
            -> IconData(Icons.Outlined.NightsStay, Color(0xFFB0BEC5))

        else -> IconData(Icons.Filled.WbSunny, ColorSunny)
    }
}