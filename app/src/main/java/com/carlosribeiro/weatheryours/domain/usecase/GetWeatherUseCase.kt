package com.carlosribeiro.weatheryours.domain.usecase

import com.carlosribeiro.weatheryours.domain.model.Weather
import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository

class GetWeatherUseCase(
    internal val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Weather {
        return repository.getWeather(city)
    }
}
