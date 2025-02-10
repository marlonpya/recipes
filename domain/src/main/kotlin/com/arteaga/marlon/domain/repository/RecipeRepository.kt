
package com.arteaga.marlon.domain.repository

import com.arteaga.marlon.domain.models.Recipe

interface RecipeRepository {
    suspend fun fetchRecipes(): Result<List<Recipe>>
}
