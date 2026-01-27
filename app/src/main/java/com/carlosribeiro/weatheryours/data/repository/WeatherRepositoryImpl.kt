package com.carlosribeiro.weatheryours.data.repository

import com.carlosribeiro.weatheryours.data.remote.WeatherApi
import com.carlosribeiro.weatheryours.domain.model.Weather
import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val apiKey: String
) : WeatherRepository {

    override suspend fun getWeather(city: String): Weather {
        val response = api.getCurrentWeather(
            city = city,
            apiKey = apiKey
        )

        return Weather(
            temperature = response.main.temp,
            description = response.weather.firstOrNull()?.description ?: "",
            city = response.name
        )
    }
}
