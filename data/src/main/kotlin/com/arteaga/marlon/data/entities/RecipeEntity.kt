
package com.arteaga.marlon.data.entities

data class RecipeEntity(
    val id: Int?,
    val name: String?,
    val image: String?,
    val description: String?,
    val location: LocationEntity?,
    val usedIngredientCount: Int?,
    val missedIngredientCount: Int?,
    val likes: Int?
)
