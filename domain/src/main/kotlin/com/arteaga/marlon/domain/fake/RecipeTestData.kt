
package com.arteaga.marlon.domain.fake

import com.arteaga.marlon.domain.models.Recipe

object RecipeTestData {
    const val id: Int = 1
    const val name: String = "Spaghetti Carbonara"
    const val image: String = "spaghetti.jpg"
    const val description: String = "A delicious Italian pasta dish."
    const val latitude: String = "42.12345"
    const val longitude: String = "12.34567"
    const val usedIngredientCount: Int = 5
    const val missedIngredientCount: Int = 2
    const val likes: Int = 100

    fun create() = Recipe(
        id = id,
        name = name,
        image = image,
        description = description,
        latitude = latitude,
        longitude = longitude,
        usedIngredientCount = usedIngredientCount,
        missedIngredientCount = missedIngredientCount,
        likes = likes
    )
}
