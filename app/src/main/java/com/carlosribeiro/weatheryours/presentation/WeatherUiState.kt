package com.carlosribeiro.weatheryours.presentation

import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel

sealed interface WeatherUiState {

    object Loading : WeatherUiState

    data class Success(
        val weather: WeatherUiModel,
        val hourlyForecast: List<HourlyForecastUiModel>
    ) : WeatherUiState

    data class Error(
        val message: String
    ) : WeatherUiState
}
