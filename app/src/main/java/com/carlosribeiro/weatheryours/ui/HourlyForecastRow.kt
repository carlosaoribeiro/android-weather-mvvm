package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
        modifier = Modifier
            .width(80.dp)
            .height(96.dp)
            .background(
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 8.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = uiModel.hour,
            color = Color.White,
            fontSize = 12.sp
        )

        Text(
            text = uiModel.temperatureText,
            color = Color.White,
            fontSize = 14.sp
        )

        Text(
            text = uiModel.description,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 10.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
    }
}

@Composable
fun HourlyForecastRow(
    items: List<HourlyForecastUiModel>
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
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
        HourlyForecastUiModel("6 AM", "0°", "overcast clouds"),
        HourlyForecastUiModel("9 AM", "8°", "scattered clouds"),
        HourlyForecastUiModel("12 PM", "3°", "light rain"),
        HourlyForecastUiModel("3 PM", "0°", "clear sky")
    )

    HourlyForecastRow(items = previewItems)
}
