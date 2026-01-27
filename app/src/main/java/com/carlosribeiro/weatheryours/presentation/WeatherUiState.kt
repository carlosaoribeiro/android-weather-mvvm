package com.carlosribeiro.weatheryours.presentation

import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel

sealed interface WeatherUiState {

    object Loading : WeatherUiState

    object RequestLocationPermission : WeatherUiState

    object FetchingLocation : WeatherUiState

    object LocationDenied : WeatherUiState

    // 🔍 Novo estado — busca manual
    object SearchByCity : WeatherUiState

    data class Success(
        val weather: WeatherUiModel,
        val hourlyForecast: List<HourlyForecastUiModel>
    ) : WeatherUiState

    data class Error(
        val message: String
    ) : WeatherUiState
}
