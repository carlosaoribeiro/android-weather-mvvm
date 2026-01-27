package com.carlosribeiro.weatheryours.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.presentation.mapper.toUi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)

    val uiState: StateFlow<WeatherUiState> = _uiState

    fun loadWeather(
        city: String,
        lat: Double,
        lon: Double
    ) {
        viewModelScope.launch {
            try {
                val weather = getWeatherUseCase(city)
                val hourly = getHourlyForecastUseCase(lat, lon)

                _uiState.value = WeatherUiState.Success(
                    weather = weather.toUi(),
                    hourlyForecast = hourly.map { it.toUi() }
                )
            } catch (e: Exception) {
                _uiState.value =
                    WeatherUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}
