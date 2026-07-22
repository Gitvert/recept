package com.example.recept.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recept.model.Recipe
import com.example.recept.ui.theme.OatBackground
import com.example.recept.ui.theme.PrimaryGreen

/**
 * App entry point: obtains the [RecipeViewModel], auto-fires sign-in + the cloud fetch on
 * first composition, and renders the loading / error / content states.
 */
@Composable
fun RecipeRoot(
    viewModel: RecipeViewModel = viewModel(factory = RecipeViewModel.Factory),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    // Bumping `attempt` re-runs sign-in + fetch; the retry button uses it.
    var attempt by remember { mutableIntStateOf(0) }
    LaunchedEffect(attempt) {
        viewModel.signInAndLoad(context)
    }

    when (val state = uiState) {
        is RecipeUiState.Loading -> LoadingScreen()
        is RecipeUiState.Error -> ErrorScreen(message = state.message, onRetry = { attempt++ })
        is RecipeUiState.Success -> RecipeApp(recipes = state.recipes)
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = PrimaryGreen)
    }
}

@Composable
private fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 20.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryGreen,
                contentColor = OatBackground,
            ),
        ) {
            Text("Försök igen")
        }
    }
}

@Composable
fun RecipeApp(recipes: List<Recipe>) {
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        val contentModifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()

        selectedRecipe?.let { recipe ->
            CookingScreen(
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
