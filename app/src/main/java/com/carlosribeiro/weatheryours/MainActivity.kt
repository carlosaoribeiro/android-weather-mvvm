package com.carlosribeiro.weatheryours

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import com.carlosribeiro.weatheryours.presentation.WeatherViewModelFactory
import com.carlosribeiro.weatheryours.presentation.ui.WeatherScreen
import com.carlosribeiro.weatheryours.ui.theme.WeatherYoursTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1️⃣ ViewModel criado via Factory
        val viewModel = WeatherViewModelFactory().create()

        setContent {
            WeatherYoursTheme {

                // 2️⃣ Apenas coleta de estado
                val state = viewModel.uiState.collectAsState().value

                // 3️⃣ Delegação TOTAL da UI
                WeatherScreen(state = state)
            }
        }

        // 4️⃣ Dispara ação inicial
        viewModel.loadWeather("São Paulo")
    }
}
