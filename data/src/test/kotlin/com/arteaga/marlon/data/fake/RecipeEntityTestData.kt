
package com.arteaga.marlon.data.fake

import com.arteaga.marlon.data.entities.LocationEntity
import com.arteaga.marlon.data.entities.RecipeEntity

object RecipeEntityTestData {

    const val recipeJsonError = "Invalid JSON"

    const val id = 1
    const val name = "Spaghetti Carbonara"
    const val image = "spaghetti.jpg"
    const val description = "A delicious Italian pasta dish."
    const val usedIngredientCount = 5
    const val missedIngredientCount = 2
    const val likes = 100

    fun createRecipeEntity(): RecipeEntity {
        return RecipeEntity(
            id = id,
            name = name,
            image = image,
            description = description,
            location = LocationEntity(
                LocationEntityTestData.latitude,
                LocationEntityTestData.longitude,
            ),
            usedIngredientCount = usedIngredientCount,
            missedIngredientCount = missedIngredientCount,
            likes = likes
        )
    }
}
