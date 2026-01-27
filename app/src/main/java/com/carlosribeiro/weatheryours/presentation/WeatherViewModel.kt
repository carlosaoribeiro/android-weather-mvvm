package com.carlosribeiro.weatheryours.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.presentation.mapper.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)

    val uiState: StateFlow<WeatherUiState> = _uiState

    init {
        onAppStart()
    }

    /**
     * App abriu → decidir fluxo inicial
     */
    private fun onAppStart() {
        // Por enquanto sempre pedimos permissão
        _uiState.value = WeatherUiState.RequestLocationPermission
    }

    /**
     * Permissão concedida pela Activity
     */
    fun onLocationPermissionGranted() {
        _uiState.value = WeatherUiState.FetchingLocation

        // ⚠️ Próxima etapa:
        // aqui vamos buscar a localização real
        // por enquanto vamos simular com Berlin
        loadWeather(
            city = "Berlin",
            lat = 52.52,
            lon = 13.41
        )
    }

    fun onLocationFetched(
        lat: Double,
        lon: Double
    ) {
        viewModelScope.launch {
            try {
                // Aqui você pode trocar depois para reverse geocode
                val cityFallback = "Current Location"

                val weather = getWeatherUseCase(cityFallback)
                val hourly = getHourlyForecastUseCase(lat, lon)

                _uiState.value = WeatherUiState.Success(
                    weather = weather.toUi(),
                    hourlyForecast = hourly.map { it.toUi() }
                )
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(
                    e.message ?: "Erro ao carregar clima"
                )
            }
        }
    }


    /**
     * Permissão negada
     */
    fun onLocationPermissionDenied() {
        _uiState.value = WeatherUiState.LocationDenied
    }

    /**
     * Usuário clicou em "Usar minha localização" novamente
     */
    fun onUseMyLocationClicked() {
        _uiState.value = WeatherUiState.RequestLocationPermission
    }

    /**
     * Fluxo existente (não quebramos nada)
     */
    fun loadWeather(
        city: String,
        lat: Double,
        lon: Double
    ) {
        viewModelScope.launch {
            try {
                val weather = getWeatherUseCase(city)
                val hourly = getHourlyForecastUseCase(lat, lon)

                _uiState.value = WeatherUiState.Success(
                    weather = weather.toUi(),
                    hourlyForecast = hourly.map { it.toUi() }
                )
            } catch (e: Exception) {
                _uiState.value =
                    WeatherUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}
