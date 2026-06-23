package com.example.recept.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recept.data.sampleRecipes
import com.example.recept.model.Recipe
import com.example.recept.ui.theme.ReceptTheme

@Composable
fun RecipeListScreen(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Recept",
            style = MaterialTheme.typography.headlineMedium,
        )

        if (recipes.isEmpty()) {
            EmptyRecipes(modifier = Modifier.weight(1f))
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(recipes) { recipe ->
                    RecipeRow(
                        recipe = recipe,
                        onClick = { onRecipeClick(recipe) },
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeRow(recipe: Recipe, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = portionLabel(recipe.portions),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Text(
                text = ingredientCountLabel(recipe.ingredients.size),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
fun EmptyRecipes(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Inga recept än",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreview() {
    ReceptTheme {
        RecipeListScreen(
            recipes = sampleRecipes,
            onRecipeClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyRecipeListScreenPreview() {
    ReceptTheme {
        RecipeListScreen(
            recipes = emptyList(),
            onRecipeClick = {},
        )
    }
}
