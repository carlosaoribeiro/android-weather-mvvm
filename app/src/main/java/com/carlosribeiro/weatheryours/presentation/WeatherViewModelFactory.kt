package com.carlosribeiro.weatheryours.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carlosribeiro.weatheryours.domain.usecase.GetAirQualityUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetDailyForecastUseCase

class WeatherViewModelFactory(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase,
    private val getAirQualityUseCase: GetAirQualityUseCase,
    private val getDailyForecastUseCase: GetDailyForecastUseCase // ✅ NOVO
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(
                getWeatherUseCase = getWeatherUseCase,
                getHourlyForecastUseCase = getHourlyForecastUseCase,
                getAirQualityUseCase = getAirQualityUseCase,
                getDailyForecastUseCase = getDailyForecastUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
