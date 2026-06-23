package com.example.recept.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recept.data.sampleRecipes
import com.example.recept.model.Recipe
import com.example.recept.ui.theme.ReceptTheme

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(24.dp),
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            TextButton(onClick = onBackClick) {
                Text(text = "Tillbaka")
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineMedium,
                )

                Text(
                    text = portionLabel(recipe.portions),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        item {
            RecipeSection(title = "Ingredienser") {
                recipe.ingredients.forEach { ingredient ->
                    Text(
                        text = "${ingredient.amount} ${ingredient.name}",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }

        item {
            RecipeSection(title = "Gör så här") {
                recipe.steps.forEachIndexed { index, step ->
                    Text(
                        text = "${index + 1}. $step",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeSection(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    ReceptTheme {
        RecipeDetailScreen(
            recipe = sampleRecipes.first(),
            onBackClick = {},
        )
    }
}
