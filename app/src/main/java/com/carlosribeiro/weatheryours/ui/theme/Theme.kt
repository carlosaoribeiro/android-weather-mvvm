package com.carlosribeiro.weatheryours.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val WeatherDarkColorScheme = darkColorScheme(
    primary               = AccentIce,
    onPrimary             = BackgroundDeep,
    primaryContainer      = AccentIceDim,
    onPrimaryContainer    = TextPrimary,

    secondary             = AccentSolar,
    onSecondary           = BackgroundDeep,
    secondaryContainer    = AccentSolarDim,
    onSecondaryContainer  = TextPrimary,

    background            = BackgroundDeep,
    onBackground          = TextPrimary,
    surface               = GlassSurface,
    onSurface             = TextPrimary,
    surfaceVariant        = GlassSurfaceStrong,
    onSurfaceVariant      = TextSecondary,

    outline               = GlassBorder,
    outlineVariant        = GlassBorderSubtle,
    error                 = Color(0xFFFF6B6B),
    onError               = Color(0xFFFFFFFF),
    scrim                 = Color(0x80000000),
)

/**
 * WeatherYoursTheme
 * Mantém o mesmo nome que o MainActivity já usa.
 * Remove dynamic color — sempre dark para o design Atmosphere Glass.
 */
@Composable
fun WeatherYoursTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = WeatherDarkColorScheme,
        typography  = WeatherTypography,
        content     = content
    )
}