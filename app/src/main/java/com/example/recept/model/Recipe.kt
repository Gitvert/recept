package com.example.recept.model

data class Recipe(
    val name: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val portions: Int,
    val isVegetarian: Boolean,
    val timeMinutes: Int,
)

data class Ingredient(
    val name: String,
    val quantity: Double,
    val unit: String,
)
