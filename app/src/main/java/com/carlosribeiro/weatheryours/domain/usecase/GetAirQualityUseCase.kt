package com.carlosribeiro.weatheryours.domain.usecase

import com.carlosribeiro.weatheryours.domain.model.AirQuality
import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository

class GetAirQualityUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(
        lat: Double,
        lon: Double
    ): AirQuality {
        return repository.getAirQuality(lat, lon)
    }
}
