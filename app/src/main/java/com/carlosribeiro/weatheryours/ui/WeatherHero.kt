package com.carlosribeiro.weatheryours.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosribeiro.weatheryours.ui.model.WeatherUiModel

@Composable
fun WeatherHero(
    uiModel: WeatherUiModel
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + slideInVertically { it / 2 }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            // 📍 Cidade
            Text(
                text = uiModel.city,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Today",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Spacer(modifier = Modifier.height(24.dp))

            // 🌡 Temperatura + ícone menor à direita
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = uiModel.temperatureText,
                    color = Color.White,
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(12.dp)) // espaçamento elegante

                WeatherIcon(
                    description = uiModel.description,
                    size = 40.dp // menor que o ícone principal
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 📝 Descrição
            Text(
                text = uiModel.description,
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 18.sp
            )
        }
    }
}

/* ---------------- PREVIEW ---------------- */

@Preview(showBackground = true, backgroundColor = 0xFF0A1E3F)
@Composable
fun WeatherHeroPreview() {
    WeatherHero(
        uiModel = WeatherUiModel(
            city = "Berlin",
            temperatureText = "24°C",
            description = "Partly Cloudy",
            humidityText = "62%",
            windSpeedText = "19 km/h",
            rainChanceText = "24%"
        )
    )
}
