package com.carlosribeiro.weatheryours.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosribeiro.weatheryours.domain.usecase.GetAirQualityUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetDailyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.presentation.mapper.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase,
    private val getAirQualityUseCase: GetAirQualityUseCase,
    private val getDailyForecastUseCase: GetDailyForecastUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(
        WeatherUiState.RequestLocationPermission
    )
    val uiState: StateFlow<WeatherUiState> = _uiState

    /* ---------- LOCATION FLOW ---------- */

    fun onLocationPermissionGranted() {
        _uiState.value = WeatherUiState.FetchingLocation
    }

    fun onLocationPermissionDenied() {
        _uiState.value = WeatherUiState.LocationDenied
    }

    fun onLocationFetched(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val weather = getWeatherUseCase.getByLocation(lat, lon)
                val hourly = getHourlyForecastUseCase(lat, lon)
                val daily = getDailyForecastUseCase(lat, lon)
                val airQuality = getAirQualityUseCase(lat, lon)

                val now = System.currentTimeMillis() / 1000

                _uiState.value = WeatherUiState.Success(
                    weather = weather.toUi(),
                    hourlyForecast = hourly.map { it.toUi() },
                    dailyForecast = daily.map { it.toUi(now) },
                    airQuality = airQuality.toUi()
                )
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(
                    e.message ?: "Generic error"
                )
            }
        }
    }

    /* ---------- SEARCH FLOW ---------- */

    fun onSearchByCityClicked() {
        _uiState.value = WeatherUiState.SearchByCity
    }

    fun loadWeatherByCity(city: String) {
        viewModelScope.launch {
            try {
                val weather = getWeatherUseCase(city)

                val hourly = getHourlyForecastUseCase(
                    weather.lat,
                    weather.lon
                )

                val daily = getDailyForecastUseCase(
                    weather.lat,
                    weather.lon
                )

                val airQuality = getAirQualityUseCase(
                    weather.lat,
                    weather.lon
                )

                val now = System.currentTimeMillis() / 1000

                _uiState.value = WeatherUiState.Success(
                    weather = weather.toUi(),
                    hourlyForecast = hourly.map { it.toUi() },
                    dailyForecast = daily.map { it.toUi(now) },
                    airQuality = airQuality.toUi()
                )
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(
                    e.message ?: "Generic error"
                )
            }
        }
    }
}
