package com.carlosribeiro.weatheryours

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosribeiro.weatheryours.data.location.LocationProvider
import com.carlosribeiro.weatheryours.data.remote.ApiFactory
import com.carlosribeiro.weatheryours.data.repository.WeatherRepositoryImpl
import com.carlosribeiro.weatheryours.domain.usecase.GetAirQualityUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetDailyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.presentation.WeatherUiState
import com.carlosribeiro.weatheryours.presentation.WeatherViewModel
import com.carlosribeiro.weatheryours.presentation.WeatherViewModelFactory
import com.carlosribeiro.weatheryours.ui.WeatherScreen
import com.carlosribeiro.weatheryours.ui.theme.WeatherYoursTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var locationProvider: LocationProvider

    private val locationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                viewModel.onLocationPermissionGranted()
                fetchLocation()
            } else {
                viewModel.onLocationPermissionDenied()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationProvider = LocationProvider(this)

        val api        = ApiFactory.createWeatherApi()
        val repository = WeatherRepositoryImpl(api)

        val factory = WeatherViewModelFactory(
            getWeatherUseCase        = GetWeatherUseCase(repository),
            getHourlyForecastUseCase = GetHourlyForecastUseCase(repository),
            getAirQualityUseCase     = GetAirQualityUseCase(repository),
            getDailyForecastUseCase  = GetDailyForecastUseCase(repository)
        )

        viewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]

        setContent {
            WeatherYoursTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle(
                    initialValue = WeatherUiState.Loading
                )

                WeatherScreen(
                    uiState                     = uiState,
                    // Lupa na tela principal → abre busca
                    onSearchClick               = { viewModel.onSearchByCityClicked() },
                    // CLOSE / seta voltar na tela de busca → volta ao estado anterior
                    onCloseSearch               = { viewModel.onCloseSearch() },
                    // Campo de busca → busca pelo nome digitado
                    onSearchByCity              = { city -> viewModel.loadWeatherByCity(city) },
                    // Telas de permissão/erro → solicita permissão
                    onRequestLocationPermission = {
                        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                )
            }
        }

        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun fetchLocation() {
        locationProvider.getLastKnownLocation(
            onSuccess = { lat, lon -> viewModel.onLocationFetched(lat, lon) },
            onError   = { viewModel.onLocationPermissionDenied() }
        )
    }
}