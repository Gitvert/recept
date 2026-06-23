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
