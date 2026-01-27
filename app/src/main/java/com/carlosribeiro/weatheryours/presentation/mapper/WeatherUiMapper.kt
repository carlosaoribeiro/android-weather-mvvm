package com.carlosribeiro.weatheryours.presentation.mapper

import com.carlosribeiro.weatheryours.domain.model.Weather
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel

fun Weather.toUi(): WeatherUiModel {
    return WeatherUiModel(
        city = city,
        temperatureText = "${temperature.toInt()}°",
        description = description
    )
}
