package com.carlosribeiro.weatheryours.presentation.mapper

import com.carlosribeiro.weatheryours.domain.model.Weather
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel
import kotlin.math.roundToInt

fun Weather.toUi(): WeatherUiModel {
    return WeatherUiModel(
        city = city,
        temperatureText = "${temperature.roundToInt()}°",
        description = description.replaceFirstChar { it.uppercase() },
        humidityText = "$humidity%",
        windSpeedText = "${windSpeed.roundToInt()} km/h",
        rainChanceText = "$rainChance%"
    )
}
