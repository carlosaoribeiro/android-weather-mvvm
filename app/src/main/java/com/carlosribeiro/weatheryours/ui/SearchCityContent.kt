package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchCityContent(
    onSearch: (String) -> Unit
) {
    var city by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Cidade") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            enabled = city.isNotBlank(),
            onClick = {
                onSearch(city.trim())
            }
        ) {
            Text("Buscar clima")
        }
    }
}
