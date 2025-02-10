
package com.arteaga.marlon.data.repositoryImp

import com.arteaga.marlon.data.ApiService
import com.arteaga.marlon.data.mapper.toModel
import com.arteaga.marlon.data.utils.handleApiResponse
import com.arteaga.marlon.domain.models.Recipe
import com.arteaga.marlon.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImp @Inject constructor(
    private val apiService: ApiService,
) : RecipeRepository {
    override suspend fun fetchRecipes(): Result<List<Recipe>> {
        return handleApiResponse(
            apiCall = { apiService.fetchRecipes() },
            transform = { entities -> entities.map { it.toModel() } }
        )
    }
}
