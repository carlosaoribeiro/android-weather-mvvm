package com.carlosribeiro.weatheryours.domain.repository

import com.carlosribeiro.weatheryours.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCity(city: String): Weather
}
