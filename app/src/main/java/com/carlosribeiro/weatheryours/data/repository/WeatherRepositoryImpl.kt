package com.carlosribeiro.weatheryours.data.repository

import com.carlosribeiro.weatheryours.BuildConfig
import com.carlosribeiro.weatheryours.data.remote.WeatherApi
import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import com.carlosribeiro.weatheryours.domain.model.Weather
import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(city: String): Weather {
        val response = api.getWeather(
            city = city,
            apiKey = BuildConfig.WEATHER_API_KEY
        )

        return Weather(
            city = response.name,
            temperature = response.main.temp,
            description = response.weather.firstOrNull()?.description.orEmpty(),
            lat = response.coord.lat,
            lon = response.coord.lon
        )
    }

    override suspend fun getHourlyForecast(
        lat: Double,
        lon: Double
    ): List<HourlyForecast> {

        val response = api.getForecast(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.WEATHER_API_KEY
        )

        return response.list.map { dto ->
            HourlyForecast(
                timestamp = dto.dt,
                temperature = dto.main.temp,
                description = dto.weather.firstOrNull()?.description.orEmpty(),
                hour = ""
            )
        }
    }
}
