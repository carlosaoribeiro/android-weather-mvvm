package com.carlosribeiro.weatheryours.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.carlosribeiro.weatheryours.R
import com.carlosribeiro.weatheryours.presentation.WeatherUiState
import com.carlosribeiro.weatheryours.ui.theme.AccentIce
import com.carlosribeiro.weatheryours.ui.theme.GlassSurfaceStrong
import com.carlosribeiro.weatheryours.ui.theme.TextPrimary
import com.carlosribeiro.weatheryours.ui.theme.TextSecondary
import com.carlosribeiro.weatheryours.ui.theme.WeatherGradient

@Composable
fun WeatherScreen(
    uiState                    : WeatherUiState,
    onSearchClick              : () -> Unit,
    onCloseSearch              : () -> Unit = {},
    onRequestLocationPermission: () -> Unit = {},
    onSearchByCity             : (String) -> Unit = {},
    modifier                   : Modifier = Modifier
) {
    when (uiState) {

        // ── Carregando ────────────────────────────────
        WeatherUiState.Loading,
        WeatherUiState.FetchingLocation -> StateFullScreen {
            CircularProgressIndicator(color = AccentIce)
            Spacer(Modifier.height(16.dp))
            Text(
                text  = if (uiState == WeatherUiState.FetchingLocation)
                    stringResource(R.string.fetching_location)
                else
                    stringResource(R.string.loading),
                style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
            )
        }

        // ── Solicitar permissão ───────────────────────
        WeatherUiState.RequestLocationPermission -> StateFullScreen {
            Icon(
                imageVector        = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint               = AccentIce,
                modifier           = Modifier.size(48.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text      = stringResource(R.string.permission_location_title),
                style     = MaterialTheme.typography.headlineSmall.copy(color = TextPrimary),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(24.dp))
            Button(onClick = onRequestLocationPermission) {
                Text(stringResource(R.string.permission_location_button))
            }
            Spacer(Modifier.height(12.dp))
            TextButton(onClick = onSearchClick) {
                Text(stringResource(R.string.search_by_city), color = TextSecondary)
            }
        }

        // ── Permissão negada ──────────────────────────
        WeatherUiState.LocationDenied -> StateFullScreen {
            Icon(
                imageVector        = Icons.Filled.LocationOff,
                contentDescription = null,
                tint               = TextSecondary,
                modifier           = Modifier.size(48.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text      = stringResource(R.string.location_denied_title),
                style     = MaterialTheme.typography.headlineSmall.copy(color = TextPrimary),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text      = stringResource(R.string.location_denied_subtitle),
                style     = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(24.dp))
            Button(onClick = onSearchClick) {
                Text(stringResource(R.string.search_by_city))
            }
        }

        // ── Busca por cidade ──────────────────────────
        WeatherUiState.SearchByCity -> SearchCityContent(
            onSearch = onSearchByCity,
            onClose  = onCloseSearch
        )

        // ── Sucesso ───────────────────────────────────
        is WeatherUiState.Success -> SuccessScreen(
            uiState       = uiState,
            onSearchClick = onSearchClick,
            modifier      = modifier
        )

        // ── Erros ─────────────────────────────────────
        is WeatherUiState.Error.Network -> ErrorScreen(
            message  = stringResource(R.string.error_network),
            onRetry  = onRequestLocationPermission,
            onSearch = onSearchClick
        )
        is WeatherUiState.Error.CityNotFound -> ErrorScreen(
            message  = stringResource(R.string.error_city_not_found),
            onRetry  = null,
            onSearch = onSearchClick
        )
        is WeatherUiState.Error.Generic -> ErrorScreen(
            message  = stringResource(R.string.error_generic),
            onRetry  = onRequestLocationPermission,
            onSearch = onSearchClick
        )
    }
}

// ─────────────────────────────────────────────
//  Tela de Sucesso
// ─────────────────────────────────────────────
@Composable
private fun SuccessScreen(
    uiState      : WeatherUiState.Success,
    onSearchClick: () -> Unit,
    modifier     : Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(WeatherGradient.backgroundFor(uiState.weather.description))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            WeatherTopBar(
                cityName      = uiState.weather.city,
                onSearchClick = onSearchClick
            )

            Box(
                modifier         = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape    = CircleShape,
                    color    = GlassSurfaceStrong,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text     = stringResource(R.string.today),
                        style    = MaterialTheme.typography.bodySmall.copy(
                            color      = TextPrimary,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            WeatherHero(
                weather  = uiState.weather,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(Modifier.height(32.dp))

            WeatherMetricsCard(
                weather  = uiState.weather,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(28.dp))

            SectionHeader(
                title    = stringResource(R.string.today),
                action   = stringResource(R.string.hourly),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(12.dp))
            if (uiState.hasHourlyForecast) {
                HourlyForecastRow(
                    items    = uiState.hourlyForecast,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(Modifier.height(28.dp))

            SectionHeader(
                title    = stringResource(R.string.five_day_forecast),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(12.dp))
            if (uiState.hasDailyForecast) {
                FiveDayForecastCard(
                    items    = uiState.dailyForecast,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

// ─────────────────────────────────────────────
//  Tela de Erro
// ─────────────────────────────────────────────
@Composable
private fun ErrorScreen(
    message : String,
    onRetry : (() -> Unit)?,
    onSearch: () -> Unit
) {
    StateFullScreen {
        Text(
            text      = "⚠️",
            style     = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text      = message,
            style     = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary),
            textAlign = TextAlign.Center,
            modifier  = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(Modifier.height(24.dp))
        if (onRetry != null) {
            Button(onClick = onRetry) { Text(stringResource(R.string.error_retry)) }
            Spacer(Modifier.height(8.dp))
        }
        TextButton(onClick = onSearch) {
            Text(stringResource(R.string.search_by_city), color = TextSecondary)
        }
    }
}

// ─────────────────────────────────────────────
//  Helper
// ─────────────────────────────────────────────
@Composable
private fun StateFullScreen(content: @Composable () -> Unit) {
    Column(
        modifier            = Modifier
            .fillMaxSize()
            .background(WeatherGradient.Default)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content             = { content() }
    )
}

// ─────────────────────────────────────────────
//  TopBar
// ─────────────────────────────────────────────
@Composable
private fun WeatherTopBar(
    cityName     : String,
    onSearchClick: () -> Unit,
    modifier     : Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector        = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint               = AccentIce,
                modifier           = Modifier.size(20.dp)
            )
            Text(
                text  = cityName,
                style = MaterialTheme.typography.displaySmall.copy(color = TextPrimary)
            )
        }
        IconButton(onClick = onSearchClick) {
            Icon(
                imageVector        = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_by_city),
                tint               = TextSecondary,
                modifier           = Modifier.size(24.dp)
            )
        }
    }
}

// ─────────────────────────────────────────────
//  SectionHeader
// ─────────────────────────────────────────────
@Composable
private fun SectionHeader(
    title   : String,
    action  : String? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(
            text  = title,
            style = MaterialTheme.typography.headlineMedium.copy(color = TextPrimary)
        )
        if (action != null) {
            Text(
                text  = action,
                style = MaterialTheme.typography.bodyMedium.copy(color = AccentIce)
            )
        }
    }
}