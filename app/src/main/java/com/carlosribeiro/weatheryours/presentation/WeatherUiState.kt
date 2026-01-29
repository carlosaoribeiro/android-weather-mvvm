package com.carlosribeiro.weatheryours.presentation

import com.carlosribeiro.weatheryours.ui.model.AirQualityUiModel
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel
import com.carlosribeiro.weatheryours.ui.model.DailyForecastUiModel

sealed interface WeatherUiState {

    /* ---------------- App lifecycle ---------------- */

    object Loading : WeatherUiState
    object RequestLocationPermission : WeatherUiState
    object FetchingLocation : WeatherUiState
    object LocationDenied : WeatherUiState
    object SearchByCity : WeatherUiState

    /* ---------------- Success ---------------- */

    data class Success(
        val weather: WeatherUiModel,
        val hourlyForecast: List<HourlyForecastUiModel>,
        val dailyForecast: List<DailyForecastUiModel>,
        val airQuality: AirQualityUiModel
    ) : WeatherUiState {

        val hasHourlyForecast: Boolean
            get() = hourlyForecast.isNotEmpty()

        val hasDailyForecast: Boolean
            get() = dailyForecast.isNotEmpty()
    }

    /* ---------------- Error states ---------------- */

    sealed interface Error : WeatherUiState {

        data class Network(
            val message: String = "Unable to connect. Check your internet connection."
        ) : Error

        data class CityNotFound(
            val message: String = "City not found. Try another name."
        ) : Error

        data class Generic(
            val message: String = "Something went wrong. Please try again."
        ) : Error
    }
}
