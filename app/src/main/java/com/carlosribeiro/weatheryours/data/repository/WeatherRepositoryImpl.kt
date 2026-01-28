package com.carlosribeiro.weatheryours.data.repository

import com.carlosribeiro.weatheryours.BuildConfig
import com.carlosribeiro.weatheryours.data.mapper.toDomain
import com.carlosribeiro.weatheryours.data.remote.WeatherApi
import com.carlosribeiro.weatheryours.data.remote.dto.WeatherResponseDto
import com.carlosribeiro.weatheryours.domain.model.AirQuality
import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import com.carlosribeiro.weatheryours.domain.model.Weather
import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.collections.firstOrNull
import com.carlosribeiro.weatheryours.data.mapper.toDailyForecast
import com.carlosribeiro.weatheryours.domain.model.DailyForecast


class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(city: String): Weather {
        require(city.isNotBlank()) {
            "City name must not be blank"
        }

        val response = api.getWeather(
            city = city,
            apiKey = BuildConfig.WEATHER_API_KEY
        )

        return response.toDomainWeather()
    }

    override suspend fun getWeatherByLocation(
        lat: Double,
        lon: Double
    ): Weather {
        val response = api.getWeatherByLocation(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.WEATHER_API_KEY
        )

        return response.toDomainWeather()
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

        val formatter = SimpleDateFormat("h a", Locale.getDefault())

        return response.list.take(4).map { dto ->
            HourlyForecast(
                timestamp = dto.dt,
                temperature = dto.main.temp,
                description = dto.weather.firstOrNull()?.description.orEmpty(),
                hour = formatter.format(Date(dto.dt * 1000))
            )
        }
    }

    override suspend fun getAirQuality(
        lat: Double,
        lon: Double
    ): AirQuality {
        val response = api.getAirQuality(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.WEATHER_API_KEY
        )

        val item = response.list.firstOrNull()
            ?: error("Air quality data not available")

        return item.toDomain()
    }

    override suspend fun getDailyForecast(
        lat: Double,
        lon: Double
    ): List <DailyForecast> {
        val response = api.getForecast(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.WEATHER_API_KEY
        )

        return response.toDailyForecast()
    }
    private fun WeatherResponseDto.toDomainWeather(): Weather {
        return Weather(
            city = name,
            temperature = main.temp,
            description = weather.firstOrNull()?.description.orEmpty(),
            lat = coord.lat,
            lon = coord.lon,
            humidity = main.humidity,
            windSpeed = wind.speed,
            rainChance = clouds.all
        )
    }
}
