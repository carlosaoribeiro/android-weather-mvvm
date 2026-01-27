package com.carlosribeiro.weatheryours.presentation

import com.carlosribeiro.weatheryours.data.remote.WeatherApi
import com.carlosribeiro.weatheryours.data.repository.WeatherRepositoryImpl
import com.carlosribeiro.weatheryours.domain.usecase.GetWeatherUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModelFactory {

    fun create(): WeatherViewModel {

        // 1️⃣ OkHttp
        val okHttpClient = OkHttpClient.Builder()
            .build()

        // 2️⃣ Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 3️⃣ API
        val weatherApi = retrofit.create(WeatherApi::class.java)

        // 4️⃣ Repository
        val repository = WeatherRepositoryImpl(
            api = weatherApi,
            apiKey = "33f08b45b730e4e9af3829e2fb2e39b0"
        )

        // 5️⃣ UseCase
        val useCase = GetWeatherUseCase(repository)

        // 6️⃣ ViewModel
        return WeatherViewModel(useCase)
    }
}
