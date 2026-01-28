package com.carlosribeiro.weatheryours.domain.model



data class Weather(
    val city: String,
    val temperature: Double,
    val description: String,
    val lat: Double,
    val lon: Double,
    val humidity: Int,
    val windSpeed: Double,
    val rainChance: Int
)