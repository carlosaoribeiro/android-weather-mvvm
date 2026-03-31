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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosribeiro.weatheryours.R
import com.carlosribeiro.weatheryours.ui.theme.AccentIce
import com.carlosribeiro.weatheryours.ui.theme.GlassBorder
import com.carlosribeiro.weatheryours.ui.theme.TextPrimary
import com.carlosribeiro.weatheryours.ui.theme.TextSecondary
import com.carlosribeiro.weatheryours.ui.theme.TextTertiary
import com.carlosribeiro.weatheryours.ui.theme.WeatherGradient

@Composable
fun SearchCityContent(
    onSearch: (String) -> Unit,
    onClose : () -> Unit = {}
) {
    var city by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) { focusRequester.requestFocus() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WeatherGradient.Default)
    ) {
        // ── TopBar ────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector        = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.close),
                    tint               = AccentIce,
                    modifier           = Modifier.size(24.dp)
                )
            }

            Text(
                text  = stringResource(R.string.search_location_title),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color      = TextPrimary,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text     = stringResource(R.string.app_name),
                style    = MaterialTheme.typography.bodyMedium.copy(
                    color      = AccentIce,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        // ── Campo de busca centralizado ───────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value         = city,
                onValueChange = { city = it },
                placeholder   = {
                    Text(
                        text  = stringResource(R.string.search_placeholder),
                        style = MaterialTheme.typography.bodyLarge.copy(color = TextTertiary)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector        = Icons.Outlined.Search,
                        contentDescription = null,
                        tint               = TextTertiary,
                        modifier           = Modifier.size(22.dp)
                    )
                },
                singleLine      = true,
                shape           = RoundedCornerShape(16.dp),
                colors          = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor      = AccentIce,
                    unfocusedBorderColor    = GlassBorder,
                    focusedTextColor        = TextPrimary,
                    unfocusedTextColor      = TextPrimary,
                    cursorColor             = AccentIce,
                    focusedContainerColor   = Color(0x1AFFFFFF),
                    unfocusedContainerColor = Color(0x0DFFFFFF)
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { if (city.isNotBlank()) onSearch(city.trim()) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text      = stringResource(R.string.search_hint),
                style     = MaterialTheme.typography.bodySmall.copy(color = TextSecondary),
                textAlign = TextAlign.Center
            )
        }

        // ── Botão CLOSE fixo no bottom ────────────────
        Box(
            modifier         = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .imePadding()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                onClick = onClose,
                shape   = RoundedCornerShape(50)
            ) {
                Icon(
                    imageVector        = Icons.Filled.Close,
                    contentDescription = null,
                    tint               = TextSecondary,
                    modifier           = Modifier.size(16.dp)
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text  = stringResource(R.string.close),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color         = TextSecondary,
                        fontWeight    = FontWeight.SemiBold,
                        letterSpacing = 2.sp
                    )
                )
            }
        }
    }
}