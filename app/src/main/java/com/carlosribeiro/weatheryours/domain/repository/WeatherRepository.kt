package com.carlosribeiro.weatheryours.domain.repository

import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import com.carlosribeiro.weatheryours.domain.model.Weather

interface WeatherRepository {

    suspend fun getWeather(
        city: String
    ): Weather

    suspend fun getWeatherByLocation(
        lat: Double,
        lon: Double
    ): Weather

    suspend fun getHourlyForecast(
        lat: Double,
        lon: Double
    ): List<HourlyForecast>
}
