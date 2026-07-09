package com.example.recept.model

import java.util.Locale
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.round
import kotlin.math.roundToInt

private const val FRACTION_TOLERANCE = 0.02

private val RECOGNIZED_FRACTIONS = listOf(
    0.25 to "¼",
    0.5 to "½",
    0.75 to "¾",
)

fun Recipe.scaledIngredients(targetPortions: Int): List<Ingredient> {
    val factor = targetPortions.toDouble() / portions
    return ingredients.map { it.copy(quantity = it.quantity * factor) }
}

fun Ingredient.displayAmount(): String = "${formatQuantity(quantity)} $unit"

fun formatQuantity(quantity: Double): String {
    val roundedWhole = quantity.roundToInt()
    if (abs(quantity - roundedWhole) <= FRACTION_TOLERANCE) {
        return roundedWhole.toString()
    }

    val whole = floor(quantity).toInt()
    val remainder = quantity - whole
    val fraction = RECOGNIZED_FRACTIONS.firstOrNull { (value, _) -> abs(remainder - value) <= FRACTION_TOLERANCE }
    if (fraction != null) {
        return if (whole == 0) fraction.second else "$whole ${fraction.second}"
    }

    return formatDecimal(quantity)
}

private fun formatDecimal(quantity: Double): String {
    val rounded = round(quantity * 100) / 100.0
    val trimmed = String.format(Locale.ROOT, "%.2f", rounded).trimEnd('0').trimEnd('.')
    return trimmed.replace('.', ',')
}
