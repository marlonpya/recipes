
package com.arteaga.marlon.domain.models

import com.arteaga.marlon.domain.fake.RecipeTestData
import org.junit.Assert.assertEquals
import org.junit.Test

class RecipeTest {
    @Test
    fun testRecipeInitialization() {
        val testData = RecipeTestData.create()
        val recipe = Recipe(
            id = testData.id,
            name = testData.name,
            image = testData.image,
            description = testData.description,
            latitude = testData.latitude,
            longitude = testData.longitude,
            usedIngredientCount = testData.usedIngredientCount,
            missedIngredientCount = testData.missedIngredientCount,
            likes = testData.likes
        )

        assertEquals(testData.id, recipe.id)
        assertEquals(testData.name, recipe.name)
        assertEquals(testData.image, recipe.image)
        assertEquals(testData.description, recipe.description)
        assertEquals(testData.latitude, recipe.latitude)
        assertEquals(testData.longitude, recipe.longitude)
        assertEquals(testData.usedIngredientCount, recipe.usedIngredientCount)
        assertEquals(testData.missedIngredientCount, recipe.missedIngredientCount)
        assertEquals(testData.likes, recipe.likes)
    }

    @Test
    fun testRecipeEquality() {
        val recipe1 = RecipeTestData.create()
        val recipe2 = RecipeTestData.create()

        assertEquals(recipe1.id, recipe2.id)
        assertEquals(recipe1.name, recipe2.name)
        assertEquals(recipe1.image, recipe2.image)
        assertEquals(recipe1.description, recipe2.description)
        assertEquals(recipe1.latitude, recipe2.latitude)
        assertEquals(recipe1.longitude, recipe2.longitude)
        assertEquals(recipe1.usedIngredientCount, recipe2.usedIngredientCount)
        assertEquals(recipe1.missedIngredientCount, recipe2.missedIngredientCount)
        assertEquals(recipe1.likes, recipe2.likes)
    }
}
