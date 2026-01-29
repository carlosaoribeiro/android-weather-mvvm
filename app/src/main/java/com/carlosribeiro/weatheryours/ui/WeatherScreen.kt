package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.safeDrawing
import com.carlosribeiro.weatheryours.presentation.WeatherUiState
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
            .padding(WindowInsets.safeDrawing.asPaddingValues())
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
                        text = "We need your location to show the weather automatically.",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onRequestLocationPermission) {
                        Text("Allow location")
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
                        text = "Getting your location...",
                        color = Color.White
                    )
                }
            }

            WeatherUiState.SearchByCity -> {
                SearchCityContent(onSearch = onSearchByCity)
            }

            WeatherUiState.LocationDenied -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Without location access we can’t show the weather automatically.",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onUseMyLocationClicked) {
                        Text("Try again")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = onSearchByCityClicked) {
                        Text("Search by city", color = Color.White)
                    }
                }
            }

            is WeatherUiState.Error -> {
                val message = when (state) {
                    is WeatherUiState.Error.Network -> state.message
                    is WeatherUiState.Error.CityNotFound -> state.message
                    is WeatherUiState.Error.Generic -> state.message
                }

                Text(
                    text = message,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

            is WeatherUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(bottom = 48.dp)
                ) {

                    item {
                        WeatherHero(uiModel = state.weather)
                    }

                    item {
                        WeatherMetricsCard(
                            humidityText = state.weather.humidityText,
                            windSpeedText = state.weather.windSpeedText,
                            rainChanceText = state.weather.rainChanceText
                        )
                    }

                    if (state.hasHourlyForecast) {
                        item {
                            HourlyForecastRow(
                                items = state.hourlyForecast
                            )
                        }
                    }

                    if (state.hasDailyForecast) {
                        item {
                            FiveDayForecastCard(
                                items = state.dailyForecast
                            )
                        }
                    }

                    item {
                        AirQualityCard(
                            model = state.airQuality
                        )
                    }
                }
            }
        }
    }
}
