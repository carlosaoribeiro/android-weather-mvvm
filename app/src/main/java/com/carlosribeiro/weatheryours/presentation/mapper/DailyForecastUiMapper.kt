package com.carlosribeiro.weatheryours.presentation.mapper

import com.carlosribeiro.weatheryours.domain.model.DailyForecast
import com.carlosribeiro.weatheryours.ui.model.DailyForecastUiModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun DailyForecast.toUi(nowEpochSeconds: Long): DailyForecastUiModel {

    val dayLabel = if (isToday(nowEpochSeconds)) {
        "Today"
    } else {
        SimpleDateFormat("EEE", Locale.getDefault())
            .format(Date(dateEpoch * 1000))
    }

    return DailyForecastUiModel(
        day = dayLabel,
        icon = mapDescriptionToIcon(description),
        minTemp = "${minTemp.toInt()}°",
        maxTemp = "${maxTemp.toInt()}°"
    )
}

/* ---------------- helpers ---------------- */

private fun DailyForecast.isToday(nowEpochSeconds: Long): Boolean {
    val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    return formatter.format(Date(dateEpoch * 1000)) ==
            formatter.format(Date(nowEpochSeconds * 1000))
}

private fun mapDescriptionToIcon(description: String): String {
    return when {
        description.contains("clear", true) -> "sunny"
        description.contains("cloud", true) -> "cloudy"
        description.contains("rain", true) -> "rain"
        description.contains("snow", true) -> "snow"
        else -> "cloudy"
    }
}
