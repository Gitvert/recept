package com.example.recept.data

import com.example.recept.model.Ingredient
import com.example.recept.model.Recipe

val sampleRecipes = listOf(
    Recipe(
        name = "Våfflor grundrecept",
        ingredients = listOf(
            Ingredient(name = "smör eller margarin", quantity = 100.0, unit = "g"),
            Ingredient(name = "vetemjöl", quantity = 5.0, unit = "dl"),
            Ingredient(name = "bakpulver", quantity = 1.5, unit = "tsk"),
            Ingredient(name = "salt", quantity = 0.5, unit = "tsk"),
            Ingredient(name = "ägg", quantity = 2.0, unit = "st"),
            Ingredient(name = "mjölk", quantity = 5.0, unit = "dl"),
        ),
        steps = listOf(
            "Smält smöret på svag värme. Låt det svalna.",
            "Blanda mjöl, bakpulver och salt i en bunke.",
            "Vispa ner ägg och mjölk i mjölblandningen till en slät smet. Vispa i smöret.",
            "Grädda våfflorna gyllenbruna i ett hett våffeljärn.",
            "Servera med sylt och vispad grädde.",
        ),
        portions = 4,
        isVegetarian = true,
        timeMinutes = 45,
    ),
    Recipe(
        name = "Pannkakor",
        ingredients = listOf(
            Ingredient(name = "vetemjöl", quantity = 2.5, unit = "dl"),
            Ingredient(name = "mjölk", quantity = 6.0, unit = "dl"),
            Ingredient(name = "ägg", quantity = 3.0, unit = "st"),
            Ingredient(name = "salt", quantity = 0.5, unit = "tsk"),
            Ingredient(name = "smör", quantity = 2.0, unit = "msk"),
        ),
        steps = listOf(
            "Vispa ihop mjöl och hälften av mjölken",
            "Vispa ner resten av mjölken, äggen och saltet",
            "Stek tunna pannkakor i smör",
        ),
        portions = 4,
        isVegetarian = true,
        timeMinutes = 30,
    ),
    Recipe(
        name = "Tomatpasta",
        ingredients = listOf(
            Ingredient(name = "pasta", quantity = 400.0, unit = "g"),
            Ingredient(name = "krossade tomater", quantity = 800.0, unit = "g"),
            Ingredient(name = "vitlök", quantity = 3.0, unit = "klyftor"),
            Ingredient(name = "olivolja", quantity = 2.0, unit = "msk"),
            Ingredient(name = "basilika", quantity = 1.0, unit = "kruka"),
        ),
        steps = listOf(
            "Koka pastan",
            "Fräs vitlöken och sjud tomatsåsen",
            "Vänd ihop med pasta och basilika",
        ),
        portions = 4,
        isVegetarian = true,
        timeMinutes = 30,
    ),
    Recipe(
        name = "Omelett",
        ingredients = listOf(
            Ingredient(name = "ägg", quantity = 8.0, unit = "st"),
            Ingredient(name = "mjölk", quantity = 4.0, unit = "msk"),
            Ingredient(name = "ost", quantity = 100.0, unit = "g"),
            Ingredient(name = "salt", quantity = 0.5, unit = "tsk"),
            Ingredient(name = "svartpeppar", quantity = 0.25, unit = "tsk"),
        ),
        steps = listOf(
            "Vispa ihop ägg och mjölk",
            "Stek på medelvärme",
            "Strö över ost, vik ihop och servera",
        ),
        portions = 4,
        isVegetarian = true,
        timeMinutes = 20,
    ),
)
