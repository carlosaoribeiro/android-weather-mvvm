package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.presentation.WeatherUiState
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel
import com.carlosribeiro.weatheryours.ui.theme.WeatherGradients
import com.carlosribeiro.weatheryours.ui.theme.gradientFor

@Composable
fun WeatherScreen(
    state: WeatherUiState
) {
    // 🎨 Background dinâmico
    val background = when (state) {
        is WeatherUiState.Success ->
            gradientFor(state.weather.description)

        else ->
            WeatherGradients.Default
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(24.dp)
    ) {
        when (state) {

            // ⏳ LOADING
            is WeatherUiState.Loading -> {
                WeatherSkeleton()
            }

            // ❌ ERROR
            is WeatherUiState.Error -> {
                Text(
                    text = state.message,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // ✅ SUCCESS
            is WeatherUiState.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WeatherHero(uiModel = state.weather)

                    Spacer(modifier = Modifier.height(32.dp))

                    WeatherMetricsCard()

                    Spacer(modifier = Modifier.height(32.dp))

                    HourlyForecastRow(
                        items = state.hourlyForecast
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherScreen(
        state = WeatherUiState.Success(
            weather = WeatherUiModel(
                city = "Berlin",
                temperatureText = "24°C",
                description = "Partly Cloudy"
            ),
            hourlyForecast = listOf(
                HourlyForecastUiModel("10 AM", "22°", "Sunny"),
                HourlyForecastUiModel("11 AM", "23°", "Sunny"),
                HourlyForecastUiModel("12 PM", "24°", "Cloudy"),
                HourlyForecastUiModel("1 PM", "25°", "Cloudy")
            )
        )
    )
}
