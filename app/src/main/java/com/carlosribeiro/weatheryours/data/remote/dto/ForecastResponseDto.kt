package com.carlosribeiro.weatheryours.data.remote.dto

data class ForecastResponseDto(
    val list: List<ForecastItemDto>
)

data class ForecastItemDto(
    val dt: Long,
    val main: MainDto,
    val weather: List<WeatherDto>
)

