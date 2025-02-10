
package com.arteaga.marlon.domain.models

class Recipe(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int,
    val likes: Int,
)
