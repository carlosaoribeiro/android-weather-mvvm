package com.carlosribeiro.weatheryours

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.carlosribeiro.weatheryours.presentation.WeatherViewModelFactory
import com.carlosribeiro.weatheryours.ui.WeatherRoute
import com.carlosribeiro.weatheryours.ui.theme.WeatherYoursTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = WeatherViewModelFactory().create()

        setContent {
            WeatherYoursTheme {
                WeatherRoute(
                    viewModel = viewModel
                )
            }
        }
    }
}
