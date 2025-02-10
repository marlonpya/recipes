
package com.arteaga.marlon.data.entities

import com.squareup.moshi.Json

class RecipeEntity(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "image") val image: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "location") val location: LocationEntity?,
    @Json(name = "usedIngredientCount") val usedIngredientCount: Int?,
    @Json(name = "missedIngredientCount") val missedIngredientCount: Int?,
    @Json(name = "likes") val likes: Int?,
)
