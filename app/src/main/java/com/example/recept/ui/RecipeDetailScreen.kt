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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recept.data.sampleRecipes
import com.example.recept.model.Ingredient
import com.example.recept.model.Recipe
import com.example.recept.model.displayAmount
import com.example.recept.model.scaledIngredients
import com.example.recept.ui.theme.ReceptTheme

private const val MIN_PORTIONS = 1

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var portions by rememberSaveable { mutableIntStateOf(recipe.portions) }
    val scaledIngredients = recipe.scaledIngredients(portions)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item {
            TextButton(
                onClick = onBackClick,
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp),
            ) {
                Text(
                    text = "← Tillbaka",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }

        item {
            RecipeDetailHeader(
                recipe = recipe,
                portions = portions,
                onDecreasePortions = { portions = (portions - 1).coerceAtLeast(MIN_PORTIONS) },
                onIncreasePortions = { portions++ },
            )
        }

        item {
            RecipeSection(title = "Ingredienser") {
                scaledIngredients.forEach { ingredient ->
                    IngredientRow(ingredient = ingredient)
                }
            }
        }

        item {
            RecipeSection(title = "Gör så här") {
                recipe.steps.forEachIndexed { index, step ->
                    StepRow(number = index + 1, text = step)
                }
            }
        }
    }
}

@Composable
private fun RecipeDetailHeader(
    recipe: Recipe,
    portions: Int,
    onDecreasePortions: () -> Unit,
    onIncreasePortions: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary,
        )

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            PortionsStepper(
                portions = portions,
                onDecrease = onDecreasePortions,
                onIncrease = onIncreasePortions,
            )
            DetailPill(
                text = ingredientCountLabel(recipe.ingredients.size),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            DetailPill(
                text = "${recipe.steps.size} steg",
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        }

        Text(
            text = recipe.ingredients.take(4).joinToString { it.name },
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun RecipeSection(title: String, content: @Composable () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        }

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            content()
        }
    }
}

@Composable
private fun IngredientRow(ingredient: Ingredient) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 1.dp,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                shape = RoundedCornerShape(percent = 50),
                color = MaterialTheme.colorScheme.primaryContainer,
            ) {
                Text(
                    text = ingredient.displayAmount(),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1,
                    modifier = Modifier
                        .widthIn(min = 58.dp)
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                )
            }

            Text(
                text = ingredient.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun StepRow(number: Int, text: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 1.dp,
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Surface(
                modifier = Modifier.size(32.dp),
                shape = RoundedCornerShape(percent = 50),
                color = MaterialTheme.colorScheme.primary,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = number.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun DetailPill(text: String, containerColor: Color, contentColor: Color) {
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

@Composable
private fun PortionsStepper(portions: Int, onDecrease: () -> Unit, onIncrease: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(percent = 50),
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
        ) {
            StepperButton(symbol = "−", onClick = onDecrease, enabled = portions > MIN_PORTIONS)
            Text(
                text = portionLabel(portions),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(horizontal = 2.dp),
            )
            StepperButton(symbol = "+", onClick = onIncrease, enabled = true)
        }
    }
}

@Composable
private fun StepperButton(symbol: String, onClick: () -> Unit, enabled: Boolean) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(percent = 50),
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = symbol,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
            )
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
