
package com.arteaga.marlon.data.mapper

import com.arteaga.marlon.data.entities.RecipeEntity
import com.arteaga.marlon.domain.models.Recipe

fun RecipeEntity.toModel() = Recipe(
    id = id ?: 0,
    name = name.orEmpty(),
    image = image.orEmpty(),
    description = description.orEmpty(),
    latitude = location?.latitude.orEmpty(),
    longitude = location?.longitude.orEmpty(),
    usedIngredientCount = usedIngredientCount ?: 0,
    missedIngredientCount = missedIngredientCount ?: 0,
    likes = likes ?: 0,
)
