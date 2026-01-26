package com.carlosribeiro.weatheryours

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carlosribeiro.weatheryours.presentation.WeatherUiState
import com.carlosribeiro.weatheryours.presentation.WeatherViewModel
import com.carlosribeiro.weatheryours.ui.theme.WeatherYoursTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherYoursTheme {

                val viewModel = WeatherViewModel()
                val state = viewModel.uiState.collectAsState().value

                when (state) {
                    is WeatherUiState.Loading -> Text("Loading...")
                    is WeatherUiState.Success -> Text("Weather loaded")
                    is WeatherUiState.Error -> Text("Error loading weather")
                }
            }
        }
    }
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherYoursTheme {
        Greeting("Android")
    }
}