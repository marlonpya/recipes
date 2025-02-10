
package com.arteaga.marlon.domain.usecase

import com.arteaga.marlon.domain.models.Recipe
import com.arteaga.marlon.domain.repository.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository,
    @Named("DispatcherIO") val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Result<List<Recipe>> {
        return withContext(dispatcher) { repository.fetchRecipes() }
    }
}
