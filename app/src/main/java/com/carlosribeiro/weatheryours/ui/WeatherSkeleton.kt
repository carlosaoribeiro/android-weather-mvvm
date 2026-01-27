package com.carlosribeiro.weatheryours.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WeatherSkeleton() {
    val transition = rememberInfiniteTransition(label = "shimmer")

    val translateX = transition.animateFloat(
        initialValue = -300f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing)
        ),
        label = "translate"
    )

    val shimmerBrush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.15f),
            Color.White.copy(alpha = 0.35f),
            Color.White.copy(alpha = 0.15f)
        ),
        start = androidx.compose.ui.geometry.Offset(translateX.value, 0f),
        end = androidx.compose.ui.geometry.Offset(translateX.value + 200f, 0f)
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SkeletonBox(120.dp, 20.dp, shimmerBrush)
        Spacer(modifier = Modifier.height(8.dp))
        SkeletonBox(80.dp, 14.dp, shimmerBrush)

        Spacer(modifier = Modifier.height(32.dp))

        SkeletonBox(96.dp, 96.dp, shimmerBrush)

        Spacer(modifier = Modifier.height(32.dp))

        SkeletonBox(160.dp, 64.dp, shimmerBrush)
        Spacer(modifier = Modifier.height(12.dp))
        SkeletonBox(120.dp, 18.dp, shimmerBrush)
    }
}

@Composable
private fun SkeletonBox(
    width: Dp,
    height: Dp,
    brush: Brush
) {
    Box(
        modifier = Modifier
            .size(width, height)
            .background(
                brush = brush,
                shape = RoundedCornerShape(8.dp)
            )
    )
}
