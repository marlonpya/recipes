
package com.arteaga.marlon.data.mapper

import com.arteaga.marlon.data.fake.LocationEntityTestData
import com.arteaga.marlon.data.fake.RecipeEntityTestData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class RecipeMapperTest {
    @Test
    fun testRecipeEntityInitialization() {
        val entity = RecipeEntityTestData.createRecipeEntity()

        with(RecipeEntityTestData) {
            assertEquals(id, entity.id)
            assertEquals(name, entity.name)
            assertEquals(image, entity.image)
            assertEquals(description, entity.description)

            val locationEntity = entity.location
            assertNotNull(locationEntity)
            assertEquals(LocationEntityTestData.latitude, locationEntity?.latitude)
            assertEquals(LocationEntityTestData.longitude, locationEntity?.longitude)

            assertEquals(usedIngredientCount, entity.usedIngredientCount)
            assertEquals(missedIngredientCount, entity.missedIngredientCount)
            assertEquals(likes, entity.likes)
        }
    }

    @Test
    fun testRecipeEntityEquality() {
        val recipeEntity1 = RecipeEntityTestData.createRecipeEntity()
        val recipeEntity2 = RecipeEntityTestData.createRecipeEntity()

        assertEquals(recipeEntity1.id, recipeEntity2.id)
    }
}
