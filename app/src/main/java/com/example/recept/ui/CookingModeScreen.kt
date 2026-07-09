package com.example.recept.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recept.R
import com.example.recept.data.sampleRecipes
import com.example.recept.model.Ingredient
import com.example.recept.model.Recipe
import com.example.recept.model.scaledIngredients
import com.example.recept.ui.theme.AccentGreen
import com.example.recept.ui.theme.CardBorder
import com.example.recept.ui.theme.CreamSurface
import com.example.recept.ui.theme.Hairline
import com.example.recept.ui.theme.ReceptTheme
import com.example.recept.ui.theme.SurfaceAlt

@Composable
fun CookingModeScreen(
    recipe: Recipe,
    ingredients: List<Ingredient>,
    checkedSteps: Set<Int>,
    onToggleStep: (Int) -> Unit,
    onExit: () -> Unit,
) {
    val activity = LocalContext.current.findActivity()

    DisposableEffect(Unit) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        onDispose {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    BackHandler(onBack = onExit)

    CookingModeContent(
        recipe = recipe,
        ingredients = ingredients,
        checkedSteps = checkedSteps,
        onToggleStep = onToggleStep,
        onExit = onExit,
    )
}

@Composable
private fun CookingModeContent(
    recipe: Recipe,
    ingredients: List<Ingredient>,
    checkedSteps: Set<Int>,
    onToggleStep: (Int) -> Unit,
    onExit: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentPadding = PaddingValues(start = 26.dp, top = 24.dp, end = 26.dp, bottom = 26.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    Text(
                        text = "Gör så här",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(bottom = 6.dp),
                    )
                }
                itemsIndexed(recipe.steps) { index, step ->
                    StepCard(
                        text = step,
                        checked = index in checkedSteps,
                        onToggle = { onToggleStep(index) },
                        checkboxSize = 22.dp,
                        checkboxCorner = 7.dp,
                        cornerRadius = 15.dp,
                    )
                }
            }

            VerticalDivider(color = Hairline)

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(SurfaceAlt),
                contentPadding = PaddingValues(start = 26.dp, top = 24.dp, end = 26.dp, bottom = 26.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Ingredienser",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        CloseButton(onClick = onExit)
                    }
                }
                itemsIndexed(ingredients) { _, ingredient ->
                    IngredientRow(ingredient = ingredient)
                }
            }
        }
    }
}

@Composable
private fun IngredientRow(ingredient: Ingredient) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        color = CreamSurface,
        border = BorderStroke(1.dp, CardBorder),
        shadowElevation = 1.dp,
    ) {
        IngredientLabel(
            ingredient = ingredient,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
        )
    }
}

@Composable
private fun CloseButton(onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        color = CreamSurface,
        shadowElevation = 2.dp,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "Stäng matlagningsläge",
                tint = AccentGreen,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

private tailrec fun Context.findActivity(): Activity = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> error("No Activity found for CookingModeScreen")
}

@Preview(showBackground = true, widthDp = 812, heightDp = 392)
@Composable
fun CookingModeScreenPreview() {
    val recipe = sampleRecipes.first()
    ReceptTheme {
        CookingModeContent(
            recipe = recipe,
            ingredients = recipe.scaledIngredients(recipe.portions),
            checkedSteps = setOf(0),
            onToggleStep = {},
            onExit = {},
        )
    }
}
