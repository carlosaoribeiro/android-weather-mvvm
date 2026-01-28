package com.carlosribeiro.weatheryours.presentation.mapper

import com.carlosribeiro.weatheryours.domain.model.AirQuality
import com.carlosribeiro.weatheryours.ui.model.AirQualityUiModel

fun AirQuality.toUi(): AirQualityUiModel {
    return AirQualityUiModel(
        index = index,
        level = level.name.lowercase()
            .replaceFirstChar { it.uppercase() },
        description = "Air quality index is $index, similar to yesterday at about this time."
    )
}