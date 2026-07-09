package com.example.recept.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Nordkök is light-only by design; the app does not follow the system dark setting.
private val NordicColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    onPrimary = OatBackground,
    primaryContainer = ChipStrip,
    onPrimaryContainer = PrimaryGreen,
    secondary = AccentGreen,
    onSecondary = CreamSurface,
    secondaryContainer = TagBg,
    onSecondaryContainer = TagText,
    tertiary = ItalicAccent,
    background = OatBackground,
    onBackground = TextPrimary,
    surface = CreamSurface,
    onSurface = TextPrimary,
    surfaceVariant = ChipStrip,
    onSurfaceVariant = TextSecondary,
    outline = CheckboxBorder,
    outlineVariant = Hairline,
)

@Composable
fun ReceptTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = NordicColorScheme,
        typography = Typography,
        content = content,
    )
}
