package com.carlosribeiro.weatheryours.domain.repository

import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import com.carlosribeiro.weatheryours.domain.model.Weather

interface WeatherRepository {

    // 🌤 Clima atual
    suspend fun getWeather(
        city: String
    ): Weather

    // ⏰ Forecast por hora
    suspend fun getHourlyForecast(
        lat: Double,
        lon: Double
    ): List<HourlyForecast>
}
