package com.carlosribeiro.weatheryours.data.mapper

import com.carlosribeiro.weatheryours.data.remote.dto.AirQualityItemDto
import com.carlosribeiro.weatheryours.domain.model.AirQuality
import com.carlosribeiro.weatheryours.domain.model.AirQualityLevel

fun AirQualityItemDto.toDomain(): AirQuality {
    return when (main.aqi) {
        1 -> AirQuality(25, AirQualityLevel.GOOD)
        2 -> AirQuality(50, AirQualityLevel.FAIR)
        3 -> AirQuality(71, AirQualityLevel.MODERATE)
        4 -> AirQuality(85, AirQualityLevel.POOR)
        else -> AirQuality(95, AirQualityLevel.VERY_POOR)
    }
}
