package com.carlosribeiro.weatheryours.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object WeatherGradients {

    val Sunny = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0A1E3F),
            Color(0xFF1E88E5)
        )
    )

    val Cloudy = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF37474F),
            Color(0xFF90A4AE)
        )
    )

    val Rainy = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF263238),
            Color(0xFF455A64)
        )
    )

    val Default = Sunny
}
fun gradientFor(description: String) = when {
    description.contains("rain", ignoreCase = true) ->
        WeatherGradients.Rainy

    description.contains("cloud", ignoreCase = true) ->
        WeatherGradients.Cloudy

    else ->
        WeatherGradients.Sunny
}