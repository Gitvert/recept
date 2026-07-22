package com.example.recept.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

// Recipes are stored in Firestore as a single serialized-JSON string per document
// (the `json` field), so these classes are serialized/deserialized with kotlinx.serialization
// rather than Firestore's object mapper. Default values keep decoding forgiving.
@Serializable
data class Recipe(
    val name: String = "",
    val ingredients: List<Ingredient> = emptyList(),
    val steps: List<String> = emptyList(),
    val portions: Int = 0,
    val isVegetarian: Boolean = false,
    val timeMinutes: Int = 0,
    // Not part of the serialized JSON; filled in from the Firestore document id on read.
    @Transient val id: String = "",
)

@Serializable
data class Ingredient(
    val name: String = "",
    val quantity: Double = 0.0,
    val unit: String = "",
)
