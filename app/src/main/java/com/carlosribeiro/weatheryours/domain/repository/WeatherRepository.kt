package com.carlosribeiro.weatheryours.domain.repository

import com.carlosribeiro.weatheryours.domain.model.AirQuality
import com.carlosribeiro.weatheryours.domain.model.DailyForecast
import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import com.carlosribeiro.weatheryours.domain.model.Weather

interface WeatherRepository {

    // Current weather
    suspend fun getWeather(city: String): Weather
    suspend fun getWeatherByLocation(lat: Double, lon: Double): Weather

    // Forecasts
    suspend fun getHourlyForecast(lat: Double, lon: Double): List<HourlyForecast>
    suspend fun getDailyForecast(lat: Double, lon: Double): List<DailyForecast>

    // Air quality
    suspend fun getAirQuality(lat: Double, lon: Double): AirQuality
}
