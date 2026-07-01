package com.example.recept.model

data class Recipe(
    val name: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val portions: Int,
)

data class Ingredient(
    val name: String,
    val quantity: Double,
    val unit: String,
)
