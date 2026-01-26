package com.carlosribeiro.weatheryours.presentation

sealed interface WeatherUiState {

    object Loading : WeatherUiState

    data class Success(
        val temperature: String
    ) : WeatherUiState

    data class Error(
        val message: String
    ) : WeatherUiState
}