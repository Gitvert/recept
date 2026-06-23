package com.example.recept

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.recept.data.sampleRecipes
import com.example.recept.ui.RecipeApp
import com.example.recept.ui.theme.ReceptTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReceptTheme {
                RecipeApp(recipes = sampleRecipes)
            }
        }
    }
}
