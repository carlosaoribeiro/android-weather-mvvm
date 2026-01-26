package com.carlosribeiro.weatheryours.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.carlosribeiro.weatheryours.presentation.WeatherUiState

@Composable
fun WeatherScreen(
    state: WeatherUiState
) {
    when (state) {
        is WeatherUiState.Loading -> {
            Text("Loading...")
        }

        is WeatherUiState.Success -> {
            Text("Temp: ${state.temperature}")
        }

        is WeatherUiState.Error -> {
            Text(state.message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherScreen(
        state = WeatherUiState.Success(
            temperature = "25.0"
        )
    )
}
