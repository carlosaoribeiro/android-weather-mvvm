package com.carlosribeiro.weatheryours.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("main")
    val main: MainDto,

    @SerializedName("weather")
    val weather: List<WeatherDescriptionDto>
)

data class MainDto(
    @SerializedName("temp")
    val temp: Double
)

data class WeatherDescriptionDto(
    @SerializedName("description")
    val description: String
)
