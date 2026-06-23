package com.example.recept.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.recept.model.Recipe

@Composable
fun RecipeApp(recipes: List<Recipe>) {
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val contentModifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()

        selectedRecipe?.let { recipe ->
            RecipeDetailScreen(
                recipe = recipe,
                onBackClick = { selectedRecipe = null },
                modifier = contentModifier,
            )
        } ?: RecipeListScreen(
            recipes = recipes,
            onRecipeClick = { recipe -> selectedRecipe = recipe },
            modifier = contentModifier,
        )
    }
}
