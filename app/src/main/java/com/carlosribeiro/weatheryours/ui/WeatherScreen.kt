package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.presentation.WeatherUiState
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel
import com.carlosribeiro.weatheryours.ui.theme.WeatherGradients
import com.carlosribeiro.weatheryours.ui.theme.gradientFor

@Composable
fun WeatherScreen(
    state: WeatherUiState,
    onSearchByCityClicked: () -> Unit = {},
    onSearchByCity: (String) -> Unit = {},
    onRequestLocationPermission: () -> Unit = {},
    onUseMyLocationClicked: () -> Unit = {}
) {
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

            WeatherUiState.Loading -> {
                WeatherSkeleton()
            }

            WeatherUiState.RequestLocationPermission -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Precisamos da sua localização para mostrar o clima automaticamente.",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onRequestLocationPermission) {
                        Text("Permitir localização")
                    }
                }
            }

            WeatherUiState.FetchingLocation -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color.White)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Obtendo sua localização...",
                        color = Color.White
                    )
                }
            }

            // 📍 Busca por cidade
            WeatherUiState.SearchByCity -> {
                SearchCityContent(
                    onSearch = onSearchByCity
                )
            }

            WeatherUiState.LocationDenied -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sem localização não conseguimos mostrar o clima automaticamente.",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onUseMyLocationClicked) {
                        Text("Tentar novamente")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(
                        onClick = { /* ViewModel muda para SearchByCity */ }
                    ) {
                        Text("Buscar por cidade", color = Color.White)
                    }
                }
            }

            is WeatherUiState.Error -> {
                Text(
                    text = state.message,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

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

/* ---------------- PREVIEWS ---------------- */

@Preview(showBackground = true)
@Composable
fun WeatherScreenSuccessPreview() {
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

@Preview(showBackground = true)
@Composable
fun WeatherScreenLoadingPreview() {
    WeatherScreen(state = WeatherUiState.Loading)
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenRequestPermissionPreview() {
    WeatherScreen(state = WeatherUiState.RequestLocationPermission)
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenFetchingLocationPreview() {
    WeatherScreen(state = WeatherUiState.FetchingLocation)
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenLocationDeniedPreview() {
    WeatherScreen(state = WeatherUiState.LocationDenied)
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenSearchByCityPreview() {
    WeatherScreen(state = WeatherUiState.SearchByCity)
}
