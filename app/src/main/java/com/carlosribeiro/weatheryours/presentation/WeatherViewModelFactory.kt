package com.carlosribeiro.weatheryours.presentation

import com.carlosribeiro.weatheryours.data.repository.FakeWeatherRepository
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase

class WeatherViewModelFactory {

    fun create(): WeatherViewModel {
        val repository = FakeWeatherRepository()
        val useCase = GetWeatherUseCase(repository)
        return WeatherViewModel(useCase)
    }
}
