package com.example.recept.model

import org.junit.Assert.assertEquals
import org.junit.Test

class RecipeScalingTest {

    private val recipe = Recipe(
        name = "Test",
        ingredients = listOf(
            Ingredient(name = "mjölk", quantity = 6.0, unit = "dl"),
            Ingredient(name = "ägg", quantity = 3.0, unit = "st"),
        ),
        steps = listOf("Gör något"),
        portions = 4,
        isVegetarian = true,
        timeMinutes = 30,
    )

    @Test
    fun `scaledIngredients halves quantities when portions are halved`() {
        val scaled = recipe.scaledIngredients(targetPortions = 2)

        assertEquals(3.0, scaled[0].quantity, 0.0001)
        assertEquals(1.5, scaled[1].quantity, 0.0001)
    }

    @Test
    fun `scaledIngredients doubles quantities when portions are doubled`() {
        val scaled = recipe.scaledIngredients(targetPortions = 8)

        assertEquals(12.0, scaled[0].quantity, 0.0001)
        assertEquals(6.0, scaled[1].quantity, 0.0001)
    }

    @Test
    fun `formatQuantity shows whole numbers without decimals`() {
        assertEquals("3", formatQuantity(3.0))
        assertEquals("12", formatQuantity(12.0))
    }

    @Test
    fun `formatQuantity recognizes quarter half and three quarter fractions`() {
        assertEquals("¼", formatQuantity(0.25))
        assertEquals("½", formatQuantity(0.5))
        assertEquals("¾", formatQuantity(0.75))
    }

    @Test
    fun `formatQuantity combines whole numbers with a recognized fraction`() {
        assertEquals("1 ½", formatQuantity(1.5))
        assertEquals("2 ¼", formatQuantity(2.25))
    }

    @Test
    fun `formatQuantity falls back to a trimmed decimal with comma separator`() {
        assertEquals("0,73", formatQuantity(0.73))
        assertEquals("1,3", formatQuantity(1.3))
    }
}
