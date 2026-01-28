package com.carlosribeiro.weatheryours.domain.usecase

import com.carlosribeiro.weatheryours.domain.model.DailyForecast
import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository

class GetDailyForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Double,
        lon: Double
    ): List<DailyForecast> =
        repository.getDailyForecast(lat, lon)
}
