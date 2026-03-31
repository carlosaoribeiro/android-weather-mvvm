package com.carlosribeiro.weatheryours.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Gradientes dinâmicos do app.
 * Cada condição climática possui um gradiente próprio para o background,
 * além de gradientes reutilizáveis para cards e elementos de UI.
 */
object WeatherGradient {

    // ─────────────────────────────────────────────
    //  Background — por condição climática
    // ─────────────────────────────────────────────

    /** Padrão / Névoa — dark navy profundo (tela nova) */
    val Default = Brush.verticalGradient(
        colorStops = arrayOf(
            0.00f to BackgroundDeep,
            0.45f to BackgroundMid,
            1.00f to BackgroundLight
        )
    )

    /** Ensolarado — navy com toque âmbar suave no topo */
    val Sunny = Brush.verticalGradient(
        colorStops = arrayOf(
            0.00f to Color(0xFF0A1628),
            0.30f to Color(0xFF0D1E35),
            0.70f to Color(0xFF122240),
            1.00f to BackgroundLight
        )
    )

    /** Nublado — cinza azulado frio */
    val Cloudy = Brush.verticalGradient(
        colorStops = arrayOf(
            0.00f to Color(0xFF080F1C),
            0.50f to Color(0xFF0E1929),
            1.00f to Color(0xFF131F34)
        )
    )

    /** Chuva — deep navy com acento azul */
    val Rainy = Brush.verticalGradient(
        colorStops = arrayOf(
            0.00f to Color(0xFF050D18),
            0.40f to Color(0xFF081525),
            1.00f to Color(0xFF0B1C32)
        )
    )

    /** Neve — muito escuro com toque frio quase branco */
    val Snowy = Brush.verticalGradient(
        colorStops = arrayOf(
            0.00f to Color(0xFF07111F),
            0.50f to Color(0xFF0E1E30),
            1.00f to Color(0xFF152840)
        )
    )

    // ─────────────────────────────────────────────
    //  Cards — glassmorphism
    // ─────────────────────────────────────────────

    /** Card padrão (métricas, previsão) */
    val GlassCard = Brush.linearGradient(
        colorStops = arrayOf(
            0.00f to Color(0x26FFFFFF),
            1.00f to Color(0x0DFFFFFF)
        ),
        start = Offset(0f, 0f),
        end   = Offset(0f, Float.POSITIVE_INFINITY)
    )

    /** Card selecionado / destacado (horário atual) */
    val GlassCardSelected = Brush.linearGradient(
        colorStops = arrayOf(
            0.00f to Color(0x40FFFFFF),
            1.00f to Color(0x1AFFFFFF)
        ),
        start = Offset(0f, 0f),
        end   = Offset(0f, Float.POSITIVE_INFINITY)
    )

    /** Card com acento solar (temperatura alta) */
    val SolarCard = Brush.linearGradient(
        colorStops = arrayOf(
            0.00f to Color(0x26F5A623),
            1.00f to Color(0x0DF5A623)
        ),
        start = Offset(0f, 0f),
        end   = Offset(0f, Float.POSITIVE_INFINITY)
    )

    // ─────────────────────────────────────────────
    //  Glow do hero (temperatura principal)
    // ─────────────────────────────────────────────

    /** Halo radial atrás do número de temperatura */
    val TemperatureGlow = Brush.radialGradient(
        colorStops = arrayOf(
            0.00f to Color(0x30F5A623),
            0.50f to Color(0x10F5A623),
            1.00f to Color(0x00F5A623)
        ),
        radius = 300f
    )

    // ─────────────────────────────────────────────
    //  Bottom navigation bar
    // ─────────────────────────────────────────────

    /** Gradiente de fade na borda superior do nav bar */
    val NavBarFade = Brush.verticalGradient(
        colorStops = arrayOf(
            0.00f to Color(0x00050D1A),
            1.00f to Color(0xFF050D1A)
        )
    )

    // ─────────────────────────────────────────────
    //  Shimmer (skeleton loading)
    // ─────────────────────────────────────────────

    fun shimmer(progress: Float): Brush = Brush.linearGradient(
        colorStops = arrayOf(
            (progress - 0.3f).coerceIn(0f, 1f) to ShimmerBase,
            progress.coerceIn(0f, 1f)           to ShimmerHighlight,
            (progress + 0.3f).coerceIn(0f, 1f)  to ShimmerBase
        ),
        start = Offset(0f, 0f),
        end   = Offset(Float.POSITIVE_INFINITY, 0f)
    )

    // ─────────────────────────────────────────────
    //  Helpers
    // ─────────────────────────────────────────────

    /**
     * Retorna o gradiente de background correto
     * com base na descrição da condição climática.
     */
    fun backgroundFor(condition: String): Brush = when {
        condition.contains("sol", ignoreCase = true)    -> Sunny
        condition.contains("nublad", ignoreCase = true) -> Cloudy
        condition.contains("chuv", ignoreCase = true)   -> Rainy
        condition.contains("nev", ignoreCase = true)    -> Snowy
        else                                             -> Default
    }
}