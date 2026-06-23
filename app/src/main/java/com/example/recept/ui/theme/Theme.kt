package com.example.recept.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Herb200,
    onPrimary = Herb900,
    primaryContainer = Herb700,
    onPrimaryContainer = White,
    secondary = Tomato200,
    onSecondary = Tomato700,
    secondaryContainer = Tomato700,
    onSecondaryContainer = White,
    tertiary = Honey200,
    onTertiary = Honey700,
    tertiaryContainer = Blue700,
    onTertiaryContainer = White,
    background = Color(0xFF101713),
    onBackground = Color(0xFFE7EFE7),
    surface = Color(0xFF18211C),
    onSurface = Color(0xFFE7EFE7),
    surfaceVariant = Color(0xFF26342B),
    onSurfaceVariant = Color(0xFFC2CDC3),
    outline = Color(0xFF7D8B80),
    outlineVariant = Color(0xFF354339),
)

private val LightColorScheme = lightColorScheme(
    primary = Herb700,
    onPrimary = White,
    primaryContainer = Herb200,
    onPrimaryContainer = Herb900,
    secondary = Tomato700,
    onSecondary = White,
    secondaryContainer = Tomato200,
    onSecondaryContainer = Tomato700,
    tertiary = Blue700,
    onTertiary = White,
    tertiaryContainer = Blue200,
    onTertiaryContainer = Blue700,
    background = Herb100,
    onBackground = Ink900,
    surface = White,
    onSurface = Ink900,
    surfaceVariant = Mist200,
    onSurfaceVariant = Ink600,
    outline = Color(0xFFAAB8AD),
    outlineVariant = Color(0xFFD4DED6),
)

@Composable
fun ReceptTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
