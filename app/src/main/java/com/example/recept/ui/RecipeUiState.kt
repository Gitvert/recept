package com.example.recept.ui

import com.example.recept.model.Recipe

/** The three states the recipe list can be in while loading from the cloud. */
sealed interface RecipeUiState {
    data object Loading : RecipeUiState

    data class Success(val recipes: List<Recipe>) : RecipeUiState

    /** Sign-in or fetch failed; [message] is a short, user-facing Swedish string. */
    data class Error(val message: String) : RecipeUiState
}
