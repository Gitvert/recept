package com.example.recept.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recept.R
import com.example.recept.data.sampleRecipes
import com.example.recept.model.Ingredient
import com.example.recept.model.Recipe
import com.example.recept.model.scaledIngredients
import com.example.recept.ui.theme.AccentGreen
import com.example.recept.ui.theme.CardBorder
import com.example.recept.ui.theme.ChipStrip
import com.example.recept.ui.theme.CreamSurface
import com.example.recept.ui.theme.Hairline
import com.example.recept.ui.theme.InfoPanel
import com.example.recept.ui.theme.ItalicAccent
import com.example.recept.ui.theme.OatBackground
import com.example.recept.ui.theme.PrimaryGreen
import com.example.recept.ui.theme.ReceptTheme

private const val MIN_PORTIONS = 1
private const val MAX_PORTIONS = 20

private val IntSetSaver = Saver<Set<Int>, IntArray>(
    save = { it.toIntArray() },
    restore = { it.toSet() },
)

@Composable
fun CookingScreen(
    recipe: Recipe,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var portions by rememberSaveable { mutableIntStateOf(recipe.portions) }
    var isCookingModeActive by rememberSaveable { mutableStateOf(false) }
    var checkedSteps by rememberSaveable(stateSaver = IntSetSaver) { mutableStateOf(emptySet()) }
    val scaledIngredients = recipe.scaledIngredients(portions)

    fun toggleStep(index: Int) {
        checkedSteps = if (index in checkedSteps) checkedSteps - index else checkedSteps + index
    }

    if (isCookingModeActive) {
        CookingModeScreen(
            recipe = recipe,
            ingredients = scaledIngredients,
            checkedSteps = checkedSteps,
            onToggleStep = ::toggleStep,
            onExit = { isCookingModeActive = false },
        )
        return
    }

    BackHandler(onBack = onBackClick)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        DetailHeader(title = recipe.name, onBackClick = onBackClick)

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Ingredienser",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        PortionStepper(
                            portions = portions,
                            onDecrease = { portions = (portions - 1).coerceAtLeast(MIN_PORTIONS) },
                            onIncrease = { portions = (portions + 1).coerceAtMost(MAX_PORTIONS) },
                        )
                    }
                }

                itemsIndexed(scaledIngredients) { _, ingredient ->
                    IngredientCard(
                        ingredient = ingredient,
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                }

                item {
                    Column(modifier = Modifier.padding(start = 20.dp, top = 16.dp, end = 20.dp)) {
                        Text(
                            text = timeEyebrowLabel(recipe.timeMinutes),
                            style = MaterialTheme.typography.labelLarge,
                            fontStyle = FontStyle.Italic,
                            color = ItalicAccent,
                        )
                        Text(
                            text = "Gör så här",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(bottom = 4.dp),
                        )
                    }
                }

                item {
                    CookingModePanel(
                        onStartCooking = { isCookingModeActive = true },
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                }

                itemsIndexed(recipe.steps) { index, step ->
                    StepCard(
                        text = step,
                        checked = index in checkedSteps,
                        onToggle = { toggleStep(index) },
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                }
            }

            CookingFab(
                onClick = { isCookingModeActive = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 18.dp, bottom = 22.dp),
            )
        }
    }
}

@Composable
private fun DetailHeader(title: String, onBackClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, top = 6.dp, end = 18.dp, bottom = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_chevron_left),
                    contentDescription = "Tillbaka",
                    tint = AccentGreen,
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
        }
        HorizontalDivider(color = Hairline)
    }
}

@Composable
private fun PortionStepper(portions: Int, onDecrease: () -> Unit, onIncrease: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(percent = 50),
        color = CreamSurface,
        border = BorderStroke(1.dp, Hairline),
        shadowElevation = 1.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(5.dp),
        ) {
            StepperButton(
                iconRes = R.drawable.ic_minus,
                contentDescription = "Färre portioner",
                onClick = onDecrease,
                enabled = portions > MIN_PORTIONS,
            )
            Text(
                text = portionLabel(portions),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(min = 90.dp),
            )
            StepperButton(
                iconRes = R.drawable.ic_plus,
                contentDescription = "Fler portioner",
                onClick = onIncrease,
                enabled = portions < MAX_PORTIONS,
            )
        }
    }
}

@Composable
private fun StepperButton(
    iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
    enabled: Boolean,
) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        color = ChipStrip,
        modifier = Modifier.size(38.dp),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = contentDescription,
                tint = if (enabled) PrimaryGreen else MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}

@Composable
private fun IngredientCard(ingredient: Ingredient, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = CreamSurface,
        border = BorderStroke(1.dp, CardBorder),
        shadowElevation = 1.dp,
    ) {
        IngredientLabel(
            ingredient = ingredient,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 15.dp),
        )
    }
}

@Composable
private fun CookingModePanel(onStartCooking: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(InfoPanel, RoundedCornerShape(18.dp))
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(
            text = "Se ingredienser och tillagningssteg bredvid varandra.",
            style = MaterialTheme.typography.bodySmall,
            color = AccentGreen,
        )
        Button(
            onClick = onStartCooking,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryGreen,
                contentColor = OatBackground,
            ),
            contentPadding = PaddingValues(15.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_pot),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
            Text(
                text = "Starta matlagningsläge",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 10.dp),
            )
        }
    }
}

@Composable
private fun CookingFab(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        // Cream ring around the FAB, per the design's outer box-shadow ring.
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(OatBackground.copy(alpha = 0.85f), CircleShape),
        )
        Surface(
            onClick = onClick,
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            color = PrimaryGreen,
            shadowElevation = 6.dp,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(R.drawable.ic_pot),
                    contentDescription = "Starta matlagningsläge",
                    tint = OatBackground,
                    modifier = Modifier.size(26.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CookingScreenPreview() {
    ReceptTheme {
        CookingScreen(
            recipe = sampleRecipes.first(),
            onBackClick = {},
        )
    }
}
