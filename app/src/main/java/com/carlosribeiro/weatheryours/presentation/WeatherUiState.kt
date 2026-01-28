package com.carlosribeiro.weatheryours.presentation

import com.carlosribeiro.weatheryours.ui.model.AirQualityUiModel
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel
import com.carlosribeiro.weatheryours.ui.model.DailyForecastUiModel

sealed interface WeatherUiState {

    object Loading : WeatherUiState
    object RequestLocationPermission : WeatherUiState
    object FetchingLocation : WeatherUiState
    object LocationDenied : WeatherUiState
    object SearchByCity : WeatherUiState

    data class Success(
        val weather: WeatherUiModel,
        val hourlyForecast: List<HourlyForecastUiModel>,
        val airQuality: AirQualityUiModel,
        val dailyForecast: List<DailyForecastUiModel> // ✅ NOVO
    ) : WeatherUiState

    data class Error(
        val message: String
    ) : WeatherUiState
}
