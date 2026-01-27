package com.carlosribeiro.weatheryours.domain.usecase

import com.carlosribeiro.weatheryours.domain.model.Weather
import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository

class GetWeatherByLocationUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(
        lat: Double,
        lon: Double
    ): Weather {
        return repository.getWeatherByLocation(lat, lon)
    }
}
