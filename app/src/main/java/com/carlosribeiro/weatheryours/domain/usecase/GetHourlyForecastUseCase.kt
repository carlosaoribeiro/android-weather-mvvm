package com.carlosribeiro.weatheryours.domain.usecase

import com.carlosribeiro.weatheryours.domain.model.HourlyForecast
import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository

class GetHourlyForecastUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(
        lat: Double,
        lon: Double
    ): List<HourlyForecast> {
        return repository.getHourlyForecast(lat, lon)
    }
}
