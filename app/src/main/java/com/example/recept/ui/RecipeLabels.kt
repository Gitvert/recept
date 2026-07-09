package com.example.recept.ui

internal fun portionLabel(count: Int) = if (count == 1) {
    "$count portion"
} else {
    "$count portioner"
}

internal fun ingredientCountLabel(count: Int) = if (count == 1) {
    "$count ingrediens"
} else {
    "$count ingredienser"
}

internal fun timeChipLabel(minutes: Int) = "Under $minutes min"

internal fun timeEyebrowLabel(minutes: Int) = "Under $minutes minuter"
