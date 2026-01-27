package com.carlosribeiro.weatheryours.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.carlosribeiro.weatheryours.presentation.WeatherViewModel

import com.carlosribeiro.weatheryours.presentation.ui.WeatherScreen

@Composable
fun WeatherRoute(
    viewModel: WeatherViewModel
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWeather("São Paulo")
    }

    WeatherScreen(state = state)
}
