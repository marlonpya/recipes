
package com.arteaga.marlon.data.repositoryImp

import com.arteaga.marlon.data.entities.LocationEntity
import com.arteaga.marlon.data.entities.RecipeEntity

object RecipeMock {
    val dataTake = listOf(
        RecipeEntity(
            id = 1,
            name = "demo",
            image = "image",
            description = "description",
            location = LocationEntity("121.234", "23.2345"),
            usedIngredientCount = 12,
            missedIngredientCount = 12,
            likes = 12
        ),
        RecipeEntity(
            id = null,
            name = null,
            image = null,
            description = null,
            location = null,
            usedIngredientCount = null,
            missedIngredientCount = null,
            likes = null
        )
    )
}
