package com.carlosribeiro.weatheryours.data.repository

import com.carlosribeiro.weatheryours.domain.model.Weather
import com.carlosribeiro.weatheryours.domain.repository.WeatherRepository
import kotlinx.coroutines.delay

class FakeWeatherRepository : WeatherRepository {

    override suspend fun getWeatherByCity(city: String): Weather {
        delay(1000) // simula chamada de rede
        return Weather(
            temperature = "25.0"
        )
    }
}
