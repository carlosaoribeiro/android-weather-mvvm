package com.carlosribeiro.weatheryours

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosribeiro.weatheryours.data.location.LocationProvider
import com.carlosribeiro.weatheryours.data.remote.ApiFactory
import com.carlosribeiro.weatheryours.data.repository.WeatherRepositoryImpl
import com.carlosribeiro.weatheryours.domain.usecase.GetAirQualityUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetDailyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.presentation.WeatherViewModel
import com.carlosribeiro.weatheryours.presentation.WeatherViewModelFactory
import com.carlosribeiro.weatheryours.presentation.WeatherUiState



import com.carlosribeiro.weatheryours.ui.WeatherScreen

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

        // 🔗 API + Repository
        val api = ApiFactory.createWeatherApi()
        val repository = WeatherRepositoryImpl(api)

        // 🔗 UseCases
        val getWeatherUseCase = GetWeatherUseCase(repository)
        val getHourlyForecastUseCase = GetHourlyForecastUseCase(repository)
        val getAirQualityUseCase = GetAirQualityUseCase(repository)
        val getDailyForecastUseCase = GetDailyForecastUseCase(repository) // ✅ NOVO

        // 🔗 Factory
        val factory = WeatherViewModelFactory(
            getWeatherUseCase = getWeatherUseCase,
            getHourlyForecastUseCase = getHourlyForecastUseCase,
            getAirQualityUseCase = getAirQualityUseCase,
            getDailyForecastUseCase = getDailyForecastUseCase // ✅ NOVO
        )

        viewModel = ViewModelProvider(
            this,
            factory
        )[WeatherViewModel::class.java]

        setContent {
            val state = viewModel.uiState.collectAsStateWithLifecycle(
                initialValue = WeatherUiState.Loading
            )

            WeatherScreen(
                state = state.value,
                onRequestLocationPermission = {
                    locationPermissionLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                },
                onUseMyLocationClicked = {
                    locationPermissionLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                },
                onSearchByCityClicked = {
                    viewModel.onSearchByCityClicked()
                },
                onSearchByCity = { city ->
                    viewModel.loadWeatherByCity(city)
                }
            )
        }
    }

    private fun fetchLocation() {
        locationProvider.getLastKnownLocation(
            onSuccess = { lat, lon ->
                viewModel.onLocationFetched(lat, lon)
            },
            onError = {
                viewModel.onLocationPermissionDenied()
            }
        )
    }
}
