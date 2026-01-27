package com.carlosribeiro.weatheryours.data.remote.dto

data class WeatherResponseDto(
    val name: String,
    val coord: CoordDto,
    val main: MainDto,
    val weather: List<WeatherDto>
)
