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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.safeDrawing
import com.carlosribeiro.weatheryours.presentation.WeatherUiState
import com.carlosribeiro.weatheryours.ui.model.AirQualityUiModel
import com.carlosribeiro.weatheryours.ui.model.DailyForecastUiModel
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
        is WeatherUiState.Success -> gradientFor(state.weather.description)
        else -> WeatherGradients.Default
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(vertical = 24.dp) // ⬅️ só vertical aqui
    ) {
        when (state) {

            WeatherUiState.Loading -> {
                WeatherSkeleton()
            }

            WeatherUiState.RequestLocationPermission -> {
                CenteredMessage {
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
                CenteredMessage {
                    CircularProgressIndicator(color = Color.White)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Obtendo sua localização...", color = Color.White)
                }
            }

            WeatherUiState.SearchByCity -> {
                SearchCityContent(onSearch = onSearchByCity)
            }

            WeatherUiState.LocationDenied -> {
                CenteredMessage {
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
                    TextButton(onClick = onSearchByCityClicked) {
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 48.dp
                    )
                ) {
                    item { WeatherHero(uiModel = state.weather) }

                    item {
                        WeatherMetricsCard(
                            humidityText = state.weather.humidityText,
                            windSpeedText = state.weather.windSpeedText,
                            rainChanceText = state.weather.rainChanceText
                        )
                    }

                    item { AirQualityCard(model = state.airQuality) }

                    item { HourlyForecastRow(items = state.hourlyForecast) }

                    item { FiveDayForecastCard(items = state.dailyForecast) }
                }
            }
        }
    }
}

/* ---------------- PREVIEW ---------------- */

@Preview(showBackground = true)
@Composable
fun WeatherScreenSuccessPreview() {
    WeatherScreen(
        state = WeatherUiState.Success(
            weather = WeatherUiModel(
                city = "Berlin",
                temperatureText = "24°C",
                description = "Partly Cloudy",
                humidityText = "62%",
                windSpeedText = "19 km/h",
                rainChanceText = "24%"
            ),
            hourlyForecast = listOf(
                HourlyForecastUiModel("10 AM", "22°", "Sunny"),
                HourlyForecastUiModel("11 AM", "23°", "Sunny"),
                HourlyForecastUiModel("12 PM", "24°", "Cloudy"),
                HourlyForecastUiModel("1 PM", "25°", "Cloudy")
            ),
            airQuality = AirQualityUiModel(
                index = 71,
                level = "Moderate",
                description = "Air quality index is 71, similar to yesterday at about this time."
            ),
            dailyForecast = listOf(
                DailyForecastUiModel("Today", "sunny", "-1°", "13°"),
                DailyForecastUiModel("Thu", "cloudy", "1°", "18°"),
                DailyForecastUiModel("Fri", "cloudy", "2°", "11°"),
                DailyForecastUiModel("Sat", "sunny", "-1°", "8°"),
                DailyForecastUiModel("Sun", "sunny", "-4°", "12°")
            )
        )
    )
}
@Composable
private fun CenteredMessage(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}