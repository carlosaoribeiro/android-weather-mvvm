package com.carlosribeiro.weatheryours.presentation.mapper

import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel

fun HourlyForecast.toUi(): HourlyForecastUiModel {
    return HourlyForecastUiModel(
        hour = hour,
        temperatureText = "${temperature.toInt()}°",
        description = description
    )
}
