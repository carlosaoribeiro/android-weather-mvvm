package com.carlosribeiro.weatheryours.ui.theme

import androidx.compose.ui.graphics.Color

// ─────────────────────────────────────────────
//  Background / Surface
// ─────────────────────────────────────────────
val BackgroundDeep      = Color(0xFF050D1A)   // fundo mais escuro (topo do gradiente)
val BackgroundMid       = Color(0xFF0A1628)   // meio
val BackgroundLight     = Color(0xFF0F2040)   // base do gradiente

// ─────────────────────────────────────────────
//  Glass surfaces
// ─────────────────────────────────────────────
val GlassSurface        = Color(0x1AFFFFFF)   // 10 % branco — card padrão
val GlassSurfaceStrong  = Color(0x26FFFFFF)   // 15 % branco — card selecionado / hover
val GlassBorder         = Color(0x33FFFFFF)   // 20 % branco — borda glass
val GlassBorderSubtle   = Color(0x1AFFFFFF)   // 10 % — divisores

// ─────────────────────────────────────────────
//  Text
// ─────────────────────────────────────────────
val TextPrimary         = Color(0xFFFFFFFF)
val TextSecondary       = Color(0xB3FFFFFF)   // 70 %
val TextTertiary        = Color(0x66FFFFFF)   // 40 %
val TextDisabled        = Color(0x33FFFFFF)   // 20 %

// ─────────────────────────────────────────────
//  Accent — laranja solar (igual ao ícone do sol)
// ─────────────────────────────────────────────
val AccentSolar         = Color(0xFFF5A623)   // laranja principal
val AccentSolarGlow     = Color(0x40F5A623)   // halo/glow do sol
val AccentSolarDim      = Color(0x1AF5A623)   // fundo sutil de cards solares

// ─────────────────────────────────────────────
//  Accent — azul gelo (horário selecionado, chuva)
// ─────────────────────────────────────────────
val AccentIce           = Color(0xFF4A9EF5)
val AccentIceGlow       = Color(0x334A9EF5)
val AccentIceDim        = Color(0x1A4A9EF5)

// ─────────────────────────────────────────────
//  Status / Weather conditions
// ─────────────────────────────────────────────
val ColorSunny          = Color(0xFFF5A623)
val ColorCloudy         = Color(0xFF8BAED4)
val ColorRainy          = Color(0xFF4A9EF5)
val ColorFoggy          = Color(0xFFB0C4DE)
val ColorSnowy          = Color(0xFFE8F4FD)

// ─────────────────────────────────────────────
//  Bottom Navigation
// ─────────────────────────────────────────────
val NavBarBackground    = Color(0xCC0A1628)   // 80 % — desfoque de fundo
val NavIconActive       = Color(0xFFFFFFFF)
val NavIconInactive     = Color(0x66FFFFFF)
val NavIndicator        = Color(0x26FFFFFF)

// ─────────────────────────────────────────────
//  Dividers / Strokes
// ─────────────────────────────────────────────
val DividerColor        = Color(0x1AFFFFFF)

// ─────────────────────────────────────────────
//  Shimmer (skeleton loading)
// ─────────────────────────────────────────────
val ShimmerBase         = Color(0x1AFFFFFF)
val ShimmerHighlight    = Color(0x40FFFFFF)