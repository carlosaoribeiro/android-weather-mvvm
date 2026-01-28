package com.carlosribeiro.weatheryours.data.remote.dto

data class AirQualityResponseDto(
    val list: List<AirQualityItemDto>
)

data class AirQualityItemDto(
    val main: AirQualityMainDto
)

data class AirQualityMainDto(
    val aqi: Int
)
