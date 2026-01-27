package com.carlosribeiro.weatheryours

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.carlosribeiro.weatheryours.data.location.LocationProvider
import com.carlosribeiro.weatheryours.data.remote.ApiFactory
import com.carlosribeiro.weatheryours.data.repository.WeatherRepositoryImpl
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.presentation.WeatherViewModel
import com.carlosribeiro.weatheryours.presentation.WeatherViewModelFactory
import com.carlosribeiro.weatheryours.ui.WeatherScreen

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var locationProvider: LocationProvider

    // 🔐 Launcher explained:
    // - Android cuida da permissão
    // - Activity decide o fluxo
    private val locationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                viewModel.onLocationPermissionGranted()
                fetchLocation()
            } else {
                viewModel.onLocationPermissionDenied()
                finish() // requisito definido: se negar, fecha o app
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 📍 Location provider
        locationProvider = LocationProvider(this)

        // 🔌 Infra básica (sem DI ainda)
        val api = ApiFactory.createWeatherApi()
        val repository = WeatherRepositoryImpl(api)

        val getWeatherUseCase = GetWeatherUseCase(repository)
        val getHourlyForecastUseCase = GetHourlyForecastUseCase(repository)

        val factory = WeatherViewModelFactory(
            getWeatherUseCase = getWeatherUseCase,
            getHourlyForecastUseCase = getHourlyForecastUseCase
        )

        viewModel = ViewModelProvider(
            this,
            factory
        )[WeatherViewModel::class.java]

        setContent {
            val state = viewModel.uiState.collectAsState().value

            WeatherScreen(
                state = state,
                onRequestLocationPermission = {
                    locationPermissionLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                },
                onUseMyLocationClicked = {
                    locationPermissionLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                }
            )
        }
    }

    /**
     * 📡 Busca a localização real do usuário
     */
    private fun fetchLocation() {
        locationProvider.getLastKnownLocation(
            onSuccess = { lat, lon ->
                viewModel.onLocationFetched(lat, lon)
            },
            onError = {
                viewModel.onLocationPermissionDenied()
                finish()
            }
        )
    }
}
