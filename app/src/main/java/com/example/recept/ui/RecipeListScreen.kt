package com.example.recept.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recept.data.sampleRecipes
import com.example.recept.model.Recipe
import com.example.recept.ui.theme.ReceptTheme

private val RecipeAccentColors = listOf(
    Color(0xFF315C4A),
    Color(0xFF8F3D28),
    Color(0xFF315E78),
    Color(0xFF7B5812),
)

@Composable
fun RecipeListScreen(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
    initialQuery: String = "",
) {
    var query by remember { mutableStateOf(initialQuery) }
    val filteredRecipes = recipes.filter { it.name.contains(query, ignoreCase = true) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(start = 20.dp, top = 28.dp, end = 20.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            RecipeListHeader()
        }

        item {
            SearchField(
                query = query,
                onQueryChange = { query = it },
            )
        }

        if (recipes.isEmpty()) {
            item {
                ListMessage(text = "Inga recept än", modifier = Modifier.fillParentMaxSize())
            }
        } else if (filteredRecipes.isEmpty()) {
            item {
                ListMessage(text = "Inga recept matchar sökningen", modifier = Modifier.fillParentMaxSize())
            }
        } else {
            itemsIndexed(filteredRecipes) { index, recipe ->
                RecipeRow(
                    recipe = recipe,
                    accentColor = RecipeAccentColors[index % RecipeAccentColors.size],
                    onClick = { onRecipeClick(recipe) },
                )
            }
        }
    }
}

@Composable
private fun RecipeListHeader() {
    Text(
        text = "Recept",
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.primary,
    )
}

@Composable
private fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("Sök recept...") },
        singleLine = true,
    )
}

@Composable
fun RecipeRow(
    recipe: Recipe,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RecipeInitial(
                name = recipe.name,
                color = accentColor,
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    MetaPill(
                        text = portionLabel(recipe.portions),
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    MetaPill(
                        text = ingredientCountLabel(recipe.ingredients.size),
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }

                Text(
                    text = recipe.ingredients.take(3).joinToString { it.name },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Text(
                text = "→",
                style = MaterialTheme.typography.titleLarge,
                color = accentColor,
            )
        }
    }
}

@Composable
private fun ListMessage(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 1.dp,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 18.dp),
            )
        }
    }
}

@Composable
private fun RecipeInitial(name: String, color: Color) {
    Surface(
        modifier = Modifier.size(56.dp),
        shape = RoundedCornerShape(8.dp),
        color = color.copy(alpha = 0.14f),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = name.firstOrNull()?.uppercaseChar()?.toString().orEmpty(),
                style = MaterialTheme.typography.titleLarge,
                color = color,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}

@Composable
private fun MetaPill(text: String, containerColor: Color, contentColor: Color) {
    Surface(
        shape = RoundedCornerShape(percent = 50),
        color = containerColor,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = contentColor,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
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

@Preview(showBackground = true)
@Composable
fun NoSearchResultsPreview() {
    ReceptTheme {
        RecipeListScreen(
            recipes = sampleRecipes,
            onRecipeClick = {},
            initialQuery = "zzz",
        )
    }
}
