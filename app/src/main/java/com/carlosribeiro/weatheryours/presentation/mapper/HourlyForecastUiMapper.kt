package com.carlosribeiro.weatheryours.presentation.mapper

import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun HourlyForecast.toUi(): HourlyForecastUiModel {
    val formatter = SimpleDateFormat("ha", Locale.getDefault())

    return HourlyForecastUiModel(
        hour = formatter.format(Date(timestamp * 1000)),
        temperatureText = "${temperature.toInt()}°",
        description = description
    )
}
