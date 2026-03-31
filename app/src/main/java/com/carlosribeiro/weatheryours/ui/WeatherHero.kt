package com.carlosribeiro.weatheryours.ui

import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel
import com.carlosribeiro.weatheryours.ui.theme.AccentSolar
import com.carlosribeiro.weatheryours.ui.theme.TextPrimary
import com.carlosribeiro.weatheryours.ui.theme.TextSecondary

@Composable
fun WeatherHero(
    weather: WeatherUiModel,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "heroGlow")

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue  = 0.18f,
        targetValue   = 0.32f,
        animationSpec = infiniteRepeatable(
            animation  = tween(durationMillis = 2400, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    val iconScale by infiniteTransition.animateFloat(
        initialValue  = 0.95f,
        targetValue   = 1.05f,
        animationSpec = infiniteRepeatable(
            animation  = tween(durationMillis = 3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iconScale"
    )

    Column(
        modifier            = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Ícone climático animado
        WeatherIcon(
            description = weather.description,
            modifier    = Modifier
                .size(80.dp)
                .scale(iconScale)
        )

        Spacer(Modifier.height(8.dp))

        // Temperatura com glow pulsante
        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .drawBehind { drawTemperatureGlow(glowAlpha) }
            )
            Text(
                text  = weather.temperatureText,   // ex: "20°"
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize      = 96.sp,
                    fontWeight    = FontWeight.Bold,
                    color         = TextPrimary,
                    letterSpacing = (-2).sp
                )
            )
        }

        // Condição climática em maiúsculas
        Text(
            text  = weather.description.uppercase(),   // ex: "NÉVOA"
            style = MaterialTheme.typography.titleMedium.copy(
                color         = TextSecondary,
                letterSpacing = 3.sp,
                fontWeight    = FontWeight.Medium
            )
        )
    }
}

private fun DrawScope.drawTemperatureGlow(alpha: Float) {
    drawCircle(
        brush = Brush.radialGradient(
            colorStops = arrayOf(
                0.00f to AccentSolar.copy(alpha = alpha),
                0.50f to AccentSolar.copy(alpha = alpha * 0.4f),
                1.00f to Color.Transparent
            ),
            center = center,
            radius = size.minDimension / 2f
        )
    )
}