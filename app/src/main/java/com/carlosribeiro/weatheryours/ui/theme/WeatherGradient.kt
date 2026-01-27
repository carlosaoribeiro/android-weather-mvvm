package com.carlosribeiro.weatheryours.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val WeatherBackgroundGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF0A1E3F), // topo (azul escuro)
        Color(0xFF1E4FA1)  // base (azul claro)
    )
)
