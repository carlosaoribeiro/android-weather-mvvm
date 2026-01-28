package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosribeiro.weatheryours.ui.model.HourlyForecastUiModel

@Composable
fun HourlyForecastItem(
    uiModel: HourlyForecastUiModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)               // largura fixa (4 itens)
            .height(96.dp)              // altura fixa
            .background(
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp)
    ) {
        Text(
            text = uiModel.hour,
            color = Color.White,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = uiModel.temperatureText,
            color = Color.White,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = uiModel.description,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 10.sp,
            maxLines = 2,                    // ✅ ATÉ 2 LINHAS
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp               // ✅ melhora leitura
        )
    }
}

@Composable
fun HourlyForecastRow(
    items: List<HourlyForecastUiModel>
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        items.forEach { item ->
            HourlyForecastItem(uiModel = item)
        }
    }
}

/* -------------------- PREVIEW -------------------- */

@Composable
@androidx.compose.ui.tooling.preview.Preview(
    showBackground = true,
    backgroundColor = 0xFF1B4F72
)
fun HourlyForecastRowPreview() {

    val previewItems = listOf(
        HourlyForecastUiModel(
            hour = "6 AM",
            temperatureText = "0°",
            description = "overcast clouds"
        ),
        HourlyForecastUiModel(
            hour = "9 AM",
            temperatureText = "8°",
            description = "scattered clouds"
        ),
        HourlyForecastUiModel(
            hour = "12 PM",
            temperatureText = "3°",
            description = "light rain"
        ),
        HourlyForecastUiModel(
            hour = "3 PM",
            temperatureText = "0°",
            description = "clear sky"
        )
    )

    HourlyForecastRow(items = previewItems)
}
