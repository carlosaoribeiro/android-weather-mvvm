package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosribeiro.weatheryours.R

@Composable
fun WeatherMetricsCard(
    humidityText: String,
    windSpeedText: String,
    rainChanceText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White.copy(alpha = 0.12f),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {

        MetricBox {
            MetricItem(
                value = humidityText,
                label = stringResource(R.string.humidity)
            )
        }

        MetricBox {
            MetricItem(
                value = windSpeedText,
                label = stringResource(R.string.wind)
            )
        }

        MetricBox {
            MetricItem(
                value = rainChanceText,
                label = stringResource(R.string.rain)
            )
        }
    }
}

/**
 * ⚠️ IMPORTANTE:
 * weight só funciona porque agora estamos no RowScope
 */
@Composable
private fun RowScope.MetricBox(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.weight(1f),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun MetricItem(
    value: String,
    label: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.65f),
            fontSize = 12.sp
        )
    }
}
