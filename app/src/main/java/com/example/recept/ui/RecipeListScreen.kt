package com.example.recept.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recept.R
import com.example.recept.data.sampleRecipes
import com.example.recept.model.Recipe
import com.example.recept.ui.theme.AccentGreen
import com.example.recept.ui.theme.CardBorder
import com.example.recept.ui.theme.ChipStrip
import com.example.recept.ui.theme.CreamSurface
import com.example.recept.ui.theme.Hairline
import com.example.recept.ui.theme.PrimaryGreen
import com.example.recept.ui.theme.ReceptTheme
import com.example.recept.ui.theme.TagBg
import com.example.recept.ui.theme.TagText
import com.example.recept.ui.theme.TextMuted
import com.example.recept.ui.theme.TextSecondary

@Composable
fun RecipeListScreen(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
    initialQuery: String = "",
) {
    var query by remember { mutableStateOf(initialQuery) }
    val filteredRecipes = recipes.filter { it.name.contains(query, ignoreCase = true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        MetaRow(recipeCount = recipes.size)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
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
                items(filteredRecipes) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onClick = { onRecipeClick(recipe) },
                    )
                }
            }
        }
    }
}

@Composable
private fun MetaRow(recipeCount: Int) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 6.dp, end = 20.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "$recipeCount recept",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 14.sp,
                color = TextSecondary,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_sort),
                    contentDescription = null,
                    tint = AccentGreen,
                    modifier = Modifier.size(18.dp),
                )
                Text(
                    text = "Senast tillagd",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = AccentGreen,
                )
            }
        }
        HorizontalDivider(color = Hairline)
    }
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
        placeholder = { Text("Sök recept…", color = TextMuted) },
        singleLine = true,
        shape = RoundedCornerShape(percent = 50),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = CreamSurface,
            unfocusedContainerColor = CreamSurface,
            focusedBorderColor = AccentGreen,
            unfocusedBorderColor = Hairline,
        ),
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = CreamSurface,
        border = BorderStroke(1.dp, CardBorder),
        shadowElevation = 2.dp,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (recipe.isVegetarian) {
                        VegetarianTag()
                    }
                }
                ImagePlaceholder(
                    label = recipe.name,
                    modifier = Modifier
                        .size(112.dp)
                        .clip(RoundedCornerShape(18.dp)),
                )
            }

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(ChipStrip)
                    .padding(horizontal = 14.dp, vertical = 11.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                MetaChip(iconRes = R.drawable.ic_clock, text = timeChipLabel(recipe.timeMinutes))
                MetaChip(iconRes = R.drawable.ic_bottle, text = ingredientCountLabel(recipe.ingredients.size))
            }
        }
    }
}

@Composable
private fun VegetarianTag() {
    Surface(
        shape = RoundedCornerShape(percent = 50),
        color = TagBg,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_leaf),
                contentDescription = null,
                tint = TagText,
                modifier = Modifier.size(14.dp),
            )
            Text(
                text = "Vegetariskt",
                style = MaterialTheme.typography.labelSmall,
                color = TagText,
            )
        }
    }
}

@Composable
private fun MetaChip(iconRes: Int, text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = AccentGreen,
            modifier = Modifier.size(15.dp),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = PrimaryGreen,
        )
    }
}

@Composable
private fun ListMessage(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = CreamSurface,
            border = BorderStroke(1.dp, CardBorder),
            shadowElevation = 1.dp,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 18.dp),
            )
        }
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
