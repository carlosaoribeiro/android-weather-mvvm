package com.carlosribeiro.weatheryours.ui.model

data class DailyForecastUiModel(
    val day: String,
    val icon: String,      // ex: "sunny", "cloudy"
    val minTemp: String,   // "2°"
    val maxTemp: String    // "13°"
)
