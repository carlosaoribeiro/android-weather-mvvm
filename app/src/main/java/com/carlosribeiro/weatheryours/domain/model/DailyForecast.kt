package com.carlosribeiro.weatheryours.domain.model

data class DailyForecast(
    val dateEpoch: Long,      // início do dia (UTC)
    val minTemp: Double,
    val maxTemp: Double,
    val description: String
)
