package com.carlosribeiro.weatheryours.domain.model

data class DailyForecast(
    val day: String,
    val minTemp: Int,
    val maxTemp: Int,
    val condition: String
)
