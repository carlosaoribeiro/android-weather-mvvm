package com.carlosribeiro.weatheryours.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.carlosribeiro.weatheryours.presentation.WeatherUiState

@Composable
fun WeatherScreen(
    state: WeatherUiState
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is WeatherUiState.Loading -> {
                Text(text = "Loading...")
            }

            is WeatherUiState.Success -> {
                Text(text = state.uiModel.temperatureText)
            }

            is WeatherUiState.Error -> {
                Text(text = state.message)
            }
        }
    }
}
