package com.carlosribeiro.weatheryours.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ─────────────────────────────────────────────
//  Fonte — usando fontes do sistema (sem dependências extras).
//  Para trocar por uma fonte customizada, substitua os Font() abaixo
//  e adicione os arquivos em res/font/.
// ─────────────────────────────────────────────
// Exemplo com fonte customizada (opcional):
// val RobotoFamily = FontFamily(
//     Font(R.font.roboto_light,    FontWeight.Light),
//     Font(R.font.roboto_regular,  FontWeight.Normal),
//     Font(R.font.roboto_medium,   FontWeight.Medium),
//     Font(R.font.roboto_bold,     FontWeight.Bold),
// )

val WeatherTypography = Typography(

    // ── Temperatura principal (hero) ──────────────────
    // Ex: "20°" — número gigante no centro da tela
    displayLarge = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Bold,
        fontSize    = 96.sp,
        lineHeight  = 96.sp,
        letterSpacing = (-2).sp
    ),

    // ── Temperatura secundária (min/max, hourly) ──────
    displayMedium = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Normal,
        fontSize    = 48.sp,
        lineHeight  = 52.sp,
        letterSpacing = (-1).sp
    ),

    // ── Nome da cidade ────────────────────────────────
    displaySmall = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Bold,
        fontSize    = 22.sp,
        lineHeight  = 28.sp,
        letterSpacing = 0.sp
    ),

    // ── Títulos de seção (ex: "Previsão de 5 dias") ───
    headlineMedium = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.SemiBold,
        fontSize    = 18.sp,
        lineHeight  = 24.sp,
        letterSpacing = 0.sp
    ),

    // ── Cabeçalho de card ─────────────────────────────
    headlineSmall = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Medium,
        fontSize    = 16.sp,
        lineHeight  = 22.sp,
        letterSpacing = 0.sp
    ),

    // ── Label de métrica (UMIDADE, VENTO, CHUVA) ──────
    titleMedium = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.SemiBold,
        fontSize    = 14.sp,
        lineHeight  = 18.sp,
        letterSpacing = 1.sp   // uppercase tracking
    ),

    // ── Valor de métrica (95%, 4 km/h) ────────────────
    titleSmall = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Bold,
        fontSize    = 22.sp,
        lineHeight  = 26.sp,
        letterSpacing = 0.sp
    ),

    // ── Corpo principal ───────────────────────────────
    bodyLarge = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Normal,
        fontSize    = 16.sp,
        lineHeight  = 24.sp,
        letterSpacing = 0.sp
    ),

    // ── Condição climática (ex: "Névoa", "Ensolarado") ─
    bodyMedium = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Normal,
        fontSize    = 14.sp,
        lineHeight  = 20.sp,
        letterSpacing = 0.sp
    ),

    // ── Hora no carrossel (10AM, 1PM) ─────────────────
    bodySmall = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Medium,
        fontSize    = 12.sp,
        lineHeight  = 16.sp,
        letterSpacing = 0.sp
    ),

    // ── Labels tiny / chips ───────────────────────────
    labelMedium = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Medium,
        fontSize    = 11.sp,
        lineHeight  = 14.sp,
        letterSpacing = 0.5.sp
    ),

    labelSmall = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Normal,
        fontSize    = 10.sp,
        lineHeight  = 13.sp,
        letterSpacing = 0.sp
    )
)