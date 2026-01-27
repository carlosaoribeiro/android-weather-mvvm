package com.carlosribeiro.weatheryours

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.carlosribeiro.weatheryours.data.remote.ApiFactory
import com.carlosribeiro.weatheryours.data.repository.WeatherRepositoryImpl
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.presentation.WeatherViewModel
import com.carlosribeiro.weatheryours.presentation.WeatherViewModelFactory
import com.carlosribeiro.weatheryours.ui.WeatherScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔌 Infra básica (sem DI ainda)
        val api = ApiFactory.createWeatherApi()
        val repository = WeatherRepositoryImpl(api)

        val getWeatherUseCase = GetWeatherUseCase(repository)
        val getHourlyForecastUseCase = GetHourlyForecastUseCase(repository)

        val factory = WeatherViewModelFactory(
            getWeatherUseCase = getWeatherUseCase,
            getHourlyForecastUseCase = getHourlyForecastUseCase
        )

        val viewModel: WeatherViewModel =
            ViewModelProvider(this, factory)[WeatherViewModel::class.java]

        setContent {

            val state = viewModel.uiState.collectAsState().value

            androidx.compose.runtime.LaunchedEffect(Unit) {
                viewModel.loadWeather(
                    city = "Berlin",
                    lat = 52.52,
                    lon = 13.41
                )
            }

            WeatherScreen(
                state = state
            )
        }
    }
}
