package com.carlosribeiro.weatheryours.data.mapper

import com.carlosribeiro.weatheryours.data.remote.dto.ForecastResponseDto
import com.carlosribeiro.weatheryours.domain.model.DailyForecast
import java.util.Calendar
import java.util.TimeZone

fun ForecastResponseDto.toDailyForecast(): List<DailyForecast> {

    return list
        .groupBy { item ->
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.timeInMillis = item.dt * 1000
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            calendar.timeInMillis
        }
        .entries
        .take(5)
        .map { (dayStartMillis, items) ->

            val minTemp = items.minOf { it.main.temp }
            val maxTemp = items.maxOf { it.main.temp }

            val description = items
                .groupingBy { it.weather.firstOrNull()?.description.orEmpty() }
                .eachCount()
                .maxByOrNull { it.value }
                ?.key
                .orEmpty()

            DailyForecast(
                dateEpoch = dayStartMillis / 1000,
                minTemp = minTemp,
                maxTemp = maxTemp,
                description = description
            )
        }
}
