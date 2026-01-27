package com.carlosribeiro.weatheryours.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel


class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)

    val uiState: StateFlow<WeatherUiState> = _uiState

    fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                val weather = getWeatherUseCase(city)

                val uiModel = WeatherUiModel(
                    city = weather.city,
                    temperatureText = "${weather.temperature.toInt()}°C",
                    description = weather.description
                )

                _uiState.value = WeatherUiState.Success(uiModel)

            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error("Erro ao carregar clima")
            }
        }
    }
}

