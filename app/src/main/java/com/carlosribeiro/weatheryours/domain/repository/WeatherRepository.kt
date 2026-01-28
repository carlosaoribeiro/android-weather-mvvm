package com.carlosribeiro.weatheryours.domain.repository

import com.carlosribeiro.weatheryours.domain.model.AirQuality
import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import com.carlosribeiro.weatheryours.domain.model.Weather

interface WeatherRepository {

    /**
     * Busca clima pelo nome da cidade
     */
    suspend fun getWeather(
        city: String
    ): Weather

    /**
     * Busca clima por coordenadas (lat/lon)
     * ⚠️ Deve ser usado SEMPRE que houver localização real
     */
    suspend fun getWeatherByLocation(
        lat: Double,
        lon: Double
    ): Weather

    /**
     * Forecast horário por coordenadas
     */
    suspend fun getHourlyForecast(
        lat: Double,
        lon: Double
    ): List<HourlyForecast>

    /**
     * Qualidade do ar por coordenadas (AQI)
     */
    suspend fun getAirQuality(
        lat: Double,
        lon: Double
    ): AirQuality
}
