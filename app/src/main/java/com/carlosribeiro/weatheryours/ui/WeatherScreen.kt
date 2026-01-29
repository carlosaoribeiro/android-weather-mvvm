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
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
import com.carlosribeiro.weatheryours.presentation.WeatherUiState
import com.carlosribeiro.weatheryours.ui.model.*
import com.carlosribeiro.weatheryours.ui.theme.WeatherGradients
import com.carlosribeiro.weatheryours.ui.theme.gradientFor
import com.carlosribeiro.weatheryours.R

@Composable
fun WeatherScreen(
    state: WeatherUiState,
    onSearchByCityClicked: () -> Unit = {},
    onSearchByCity: (String) -> Unit = {},
    onRequestLocationPermission: () -> Unit = {},
    onUseMyLocationClicked: () -> Unit = {}
) {
    val background = if (state is WeatherUiState.Success) {
        gradientFor(state.weather.description)
    } else {
        WeatherGradients.Default
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(vertical = 24.dp)
    ) {
        when (state) {

            WeatherUiState.Loading -> {
                WeatherSkeleton()
            }

            WeatherUiState.RequestLocationPermission -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    // 🔹 CONTEÚDO CENTRAL
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // ☁️ ÍCONE
                        Text(
                            text = "🌤️",
                            fontSize = (64.sp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = stringResource(R.string.welcome_title),
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = stringResource(R.string.permission_location_title),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = 0.85f),
                            textAlign = TextAlign.Center
                        )
                    }

                    // 🔹 BOTÃO NO RODAPÉ
                    Button(
                        onClick = onRequestLocationPermission,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(24.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.permission_location_button),
                            color = Color(0xFF0A3D62),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }


            WeatherUiState.FetchingLocation -> {
                CenteredMessage {
                    CircularProgressIndicator(color = Color.White)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(R.string.fetching_location),
                        color = Color.White
                    )
                }
            }

            WeatherUiState.SearchByCity -> {
                SearchCityContent(onSearch = onSearchByCity)
            }

            WeatherUiState.LocationDenied -> {
                CenteredMessage {
                    Text(
                        text = stringResource(R.string.location_denied),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onUseMyLocationClicked) {
                        Text(stringResource(R.string.try_again))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = onSearchByCityClicked) {
                        Text(
                            text = stringResource(R.string.search_by_city),
                            color = Color.White
                        )
                    }
                }
            }

            is WeatherUiState.Error -> {
                // fallback seguro (tipagem defensiva)
                val messageRes = when (state) {
                    else -> R.string.error_generic
                }

                Text(
                    text = stringResource(messageRes),
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

                    if (state.hourlyForecast.isNotEmpty()) {
                        item {
                            HourlyForecastRow(
                                items = state.hourlyForecast
                            )
                        }
                    }

                    item {
                        AirQualityCard(
                            model = state.airQuality
                        )
                    }

                    if (state.dailyForecast.isNotEmpty()) {
                        item {
                            FiveDayForecastCard(
                                items = state.dailyForecast
                            )
                        }
                    }
                }
            }
        }
        }
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
