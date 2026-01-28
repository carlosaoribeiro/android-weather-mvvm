package com.carlosribeiro.weatheryours.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosribeiro.weatheryours.domain.usecase.GetAirQualityUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetDailyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetHourlyForecastUseCase
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import com.carlosribeiro.weatheryours.presentation.mapper.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ✅ IMPORT CORRETO
import com.carlosribeiro.weatheryours.presentation.WeatherUiState

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase,
    private val getAirQualityUseCase: GetAirQualityUseCase,
    private val getDailyForecastUseCase: GetDailyForecastUseCase
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
                val daily = getDailyForecastUseCase(lat, lon)

                val now = System.currentTimeMillis() / 1000

                _uiState.value = WeatherUiState.Success(
                    weather = weather.toUi(),
                    hourlyForecast = hourly.map { it.toUi() },
                    airQuality = airQuality.toUi(),
                    dailyForecast = daily.map { it.toUi(now) }
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
                    airQuality = airQuality.toUi(),
                    dailyForecast = emptyList() // ✅ obrigatório
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
