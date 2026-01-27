package com.carlosribeiro.weatheryours.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WeatherIcon(
    description: String,
    size: Dp = 64.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "weather-icon")

    val scale = infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "icon-scale"
    )

    val icon = when {
        description.contains("cloud", true) -> Icons.Default.Cloud
        else -> Icons.Default.WbSunny
    }

    val tint = when {
        description.contains("cloud", true) -> Color.LightGray
        else -> Color(0xFFFFC107)
    }

    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = tint,
        modifier = Modifier
            .scale(scale.value)
            .size(size)
    )
}
