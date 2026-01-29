package com.carlosribeiro.weatheryours.domain.usecase

import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(city: String) =
        repository.getWeather(city)

    suspend fun getByLocation(
        lat: Double,
        lon: Double
    ) = repository.getWeatherByLocation(lat, lon)
}
