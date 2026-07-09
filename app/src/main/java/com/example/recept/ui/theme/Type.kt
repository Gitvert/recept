package com.example.recept.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.recept.R

// Variable fonts: the wght axis is derived from the declared FontWeight per entry.
val HankenGrotesk = FontFamily(
    Font(R.font.hanken_grotesk, weight = FontWeight.Normal),
    Font(R.font.hanken_grotesk, weight = FontWeight.Medium),
    Font(R.font.hanken_grotesk, weight = FontWeight.SemiBold),
    Font(R.font.hanken_grotesk, weight = FontWeight.Bold),
    Font(R.font.hanken_grotesk, weight = FontWeight.ExtraBold),
    Font(R.font.hanken_grotesk_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.hanken_grotesk_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
)

val Typography = Typography(
    // Section titles: "Ingredienser", "Gör så här"
    headlineMedium = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.24).sp,
    ),
    // Recipe card titles and screen header titles
    titleLarge = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 23.sp,
        letterSpacing = (-0.2).sp,
    ),
    // Primary button text
    titleMedium = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
    ),
    // Portion stepper label
    titleSmall = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
    ),
    // Ingredient rows
    bodyLarge = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
    ),
    // Step rows
    bodyMedium = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 23.sp,
        letterSpacing = 0.sp,
    ),
    // Info panel copy
    bodySmall = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp,
    ),
    // Sort control, duration eyebrow (italic applied at usage site)
    labelLarge = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
    ),
    // Meta chips
    labelMedium = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
    ),
    // Vegetarian tag
    labelSmall = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
    ),
)
