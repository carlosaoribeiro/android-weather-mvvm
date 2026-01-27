package com.carlosribeiro.weatheryours.presentation.mapper

import com.carlosribeiro.weatheryours.domain.model.Weather
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel

fun Weather.toUiModel(): WeatherUiModel {
    return WeatherUiModel(
        city = city,
        temperatureText = "${temperature}°C",
        description = description
    )
}
