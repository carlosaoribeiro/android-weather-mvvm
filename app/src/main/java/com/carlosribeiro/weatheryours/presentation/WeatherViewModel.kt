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

    /* ---------------- App lifecycle ---------------- */

    private fun onAppStart() {
        _uiState.value = WeatherUiState.RequestLocationPermission
    }

    /* ---------------- Location flow ---------------- */

    fun onLocationPermissionGranted() {
        _uiState.value = WeatherUiState.FetchingLocation
    }

    fun onLocationFetched(
        lat: Double,
        lon: Double
    ) {
        viewModelScope.launch {
            try {
                // ✅ CORRETO: localização → localização
                val weather = getWeatherUseCaseByLocation(lat, lon)
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

    fun onLocationPermissionDenied() {
        _uiState.value = WeatherUiState.LocationDenied
    }

    fun onUseMyLocationClicked() {
        _uiState.value = WeatherUiState.RequestLocationPermission
    }

    /* ---------------- Manual search ---------------- */

    fun onSearchByCityClicked() {
        _uiState.value = WeatherUiState.SearchByCity
    }

    fun loadWeatherByCity(city: String) {
        viewModelScope.launch {
            try {
                // ✅ CORRETO: cidade digitada → city endpoint
                val weather = getWeatherUseCase(city)

                _uiState.value = WeatherUiState.Success(
                    weather = weather.toUi(),
                    hourlyForecast = emptyList()
                )
            } catch (e: Exception) {
                _uiState.value =
                    WeatherUiState.Error(e.message ?: "Erro ao buscar cidade")
            }
        }
    }

    /* ---------------- Internal helpers ---------------- */

    /**
     * 🔒 Garantia absoluta:
     * localização NUNCA passa por getWeather(city)
     */
    private suspend fun getWeatherUseCaseByLocation(
        lat: Double,
        lon: Double
    ) = getWeatherUseCaseByLocationInternal(lat, lon)

    /**
     * Esse método só existe para deixar explícito o contrato.
     * Ele DEVE chamar o repository por localização.
     */
    private suspend fun getWeatherUseCaseByLocationInternal(
        lat: Double,
        lon: Double
    ) = getWeatherUseCase.repository.getWeatherByLocation(lat, lon)
}
