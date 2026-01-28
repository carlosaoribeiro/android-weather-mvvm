package com.carlosribeiro.weatheryours.domain.model

data class HourlyForecast(
    val timestamp: Long,
    val temperature: Double,
    val description: String,
    val hour: String // 👈 ADICIONE ISSO

)