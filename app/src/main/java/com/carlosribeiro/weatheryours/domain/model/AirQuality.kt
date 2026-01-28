package com.carlosribeiro.weatheryours.domain.model

data class AirQuality(
    val index: Int,
    val level: AirQualityLevel
)

enum class AirQualityLevel {
    GOOD, FAIR, MODERATE, POOR, VERY_POOR
}