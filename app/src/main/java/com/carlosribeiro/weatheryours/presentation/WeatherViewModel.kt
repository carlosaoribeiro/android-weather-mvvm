package com.carlosribeiro.weatheryours.presentation

import WeatherUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosribeiro.weatheryours.domain.usecase.GetAirQualityUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.presentation.mapper.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase,
    private val getAirQualityUseCase: GetAirQualityUseCase
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
                val weather = getWeatherUseCaseByLocation(lat, lon)
                val hourly = getHourlyForecastUseCase(lat, lon)
                val airQuality = getAirQualityUseCase(lat, lon)

                _uiState.value = WeatherUiState.Success(
                    weather = weather.toUi(),
                    hourlyForecast = hourly.map { it.toUi() },
                    airQuality = airQuality.toUi()
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
                val weather = getWeatherUseCase(city)

                val airQuality = getAirQualityUseCase(
                    lat = weather.lat,
                    lon = weather.lon
                )

                _uiState.value = WeatherUiState.Success(
                    weather = weather.toUi(),
                    hourlyForecast = emptyList(),
                    airQuality = airQuality.toUi()
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
    ) = getWeatherUseCase.repository.getWeatherByLocation(lat, lon)
}
