package com.carlosribeiro.weatheryours.data.mapper

import com.carlosribeiro.weatheryours.data.remote.dto.ForecastItemDto
import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import java.text.SimpleDateFormat
import java.util.*

fun ForecastItemDto.toDomain(): HourlyForecast {
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    val hourFormatted = formatter.format(Date(dt * 1000))

    return HourlyForecast(
        timestamp = dt,
        temperature = main.temp,
        description = weather.firstOrNull()?.description.orEmpty(),
        hour = hourFormatted
    )
}
