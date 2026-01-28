package com.carlosribeiro.weatheryours.data.remote.dto




data class WeatherResponseDto(
    val name: String,
    val coord: CoordDto,
    val main: MainDto,
    val weather: List<WeatherDto>,
    val wind: WindDto,
    val clouds: CloudsDto
)

data class MainDto(
    val temp: Double,
    val humidity: Int // ✅ OBRIGATÓRIO
)

data class WindDto(
    val speed: Double
)

data class CloudsDto(
    val all: Int
)
