☀️ WeatherYours

A modern Android weather application that delivers real-time weather information with a refined UI, clean architecture, and a smooth user experience built with Kotlin and Jetpack Compose.

The project focuses on visual consistency, state-driven UI, and clear separation of concerns, making it ideal as a portfolio-ready Android app.

✅ Features


📍 Fetch weather automatically using device location

🌡️ Current temperature, description, and city display

💧 Weather metrics: humidity, wind speed, and rain chance

⏰ Hourly weather forecast with balanced cards

📆 5-day forecast with min/max temperatures

🌫️ Air quality index with descriptive levels

🎨 Dynamic background gradients based on weather conditions

🌙 Modern dark UI with refined spacing and alignment

🧪 Requirements

Android Studio (recommended: Hedgehog or newer)

Minimum Android SDK: 24

Internet connection

Optional location permission for automatic weather fetch

Permissions used:

INTERNET

ACCESS_NETWORK_STATE

ACCESS_COARSE_LOCATION

ACCESS_FINE_LOCATION

🖼️ App Screenshots



🚀 How to Use

Open the app

Allow location access or search by city

View current weather details

Scroll to explore:

Weather metrics

Hourly forecast

5-day forecast

Air quality information

🧱 Architecture Overview

The app follows Clean Architecture principles combined with MVVM, ensuring scalability and maintainability.

Main layers:

data → API, DTOs, mappers, repositories, and location provider

domain → business models, repository interfaces, and use cases

presentation → ViewModel, UI state, and UI mappers

ui → Jetpack Compose screens, components, and theming

This separation keeps business logic independent from UI and data sources.

📁 Project Structure (Simplified)
data/
 ├─ location
 ├─ mapper
 ├─ remote
 └─ repository

domain/
 ├─ model
 ├─ repository
 └─ usecase

presentation/
 ├─ mapper
 └─ ViewModel / UiState

ui/
 ├─ model
 ├─ theme
 └─ Compose components & screens

🛠️ Technologies Used

Kotlin

Jetpack Compose

MVVM + Clean Architecture

Material Design 3

State-driven UI

REST API consumption

Location Services

🎨 UI & UX Notes

Consistent spacing across all cards

Fixed and predictable layouts for forecasts

Clear visual hierarchy for weather data

Smooth scrolling and responsive composition

🔐 Privacy & Data

No personal user data is stored

Location is used only to fetch weather information

No data is shared with third parties

📌 Notes

Designed as a portfolio-focused Android project

Emphasis on UI polish and code organization

Easily extensible for future features:

Saved locations

Unit switching (°C / °F)

Weather animations
