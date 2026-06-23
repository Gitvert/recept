package com.example.recept.data

import com.example.recept.model.Ingredient
import com.example.recept.model.Recipe

val sampleRecipes = listOf(
    Recipe(
        name = "Pannkakor",
        ingredients = listOf(
            Ingredient(name = "vetemjöl", amount = "2,5 dl"),
            Ingredient(name = "mjölk", amount = "6 dl"),
            Ingredient(name = "ägg", amount = "3 st"),
            Ingredient(name = "salt", amount = "1/2 tsk"),
            Ingredient(name = "smör", amount = "2 msk"),
        ),
        steps = listOf(
            "Vispa ihop mjöl och hälften av mjölken",
            "Vispa ner resten av mjölken, äggen och saltet",
            "Stek tunna pannkakor i smör",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Tomatpasta",
        ingredients = listOf(
            Ingredient(name = "pasta", amount = "400 g"),
            Ingredient(name = "krossade tomater", amount = "800 g"),
            Ingredient(name = "vitlök", amount = "3 klyftor"),
            Ingredient(name = "olivolja", amount = "2 msk"),
            Ingredient(name = "basilika", amount = "1 kruka"),
        ),
        steps = listOf(
            "Koka pastan",
            "Fräs vitlöken och sjud tomatsåsen",
            "Vänd ihop med pasta och basilika",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Omelett",
        ingredients = listOf(
            Ingredient(name = "ägg", amount = "8 st"),
            Ingredient(name = "mjölk", amount = "4 msk"),
            Ingredient(name = "ost", amount = "100 g"),
            Ingredient(name = "salt", amount = "1/2 tsk"),
            Ingredient(name = "svartpeppar", amount = "1/4 tsk"),
        ),
        steps = listOf(
            "Vispa ihop ägg och mjölk",
            "Stek på medelvärme",
            "Strö över ost, vik ihop och servera",
        ),
        portions = 4,
    ),
)
