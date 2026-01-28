package com.carlosribeiro.weatheryours.ui.model

data class WeatherUiModel(

    val city: String,
    val temperatureText: String,
    val description: String,
    val humidityText: String,
    val windSpeedText: String,
    val rainChanceText: String
)
