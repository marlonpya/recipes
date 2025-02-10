
package com.arteaga.marlon.data.repositoryImp

import com.arteaga.marlon.data.ApiService
import com.arteaga.marlon.data.mapper.toModel
import com.arteaga.marlon.data.utils.handleApiResponse
import com.arteaga.marlon.domain.models.ErrorGeneric
import com.arteaga.marlon.domain.models.Recipe
import com.arteaga.marlon.domain.repository.RecipeRepository
import com.squareup.moshi.JsonDataException
import java.io.IOException
import java.io.InterruptedIOException
import javax.inject.Inject

class RecipeRepositoryImp @Inject constructor(
    private val apiService: ApiService,
) : RecipeRepository {
    override suspend fun fetchRecipes(): Result<List<Recipe>> {
        return try {
            val response = apiService.fetchRecipes()
            if (response.isSuccessful) {
                val body = response.body() ?: throw JsonDataException("Response body is null")
                val result = body.map { it.toModel() }
                Result.success(result)
            } else {
                val fail = ErrorGeneric(response.code(), response.message())
                Result.failure(fail)
            }
        } catch (e: Exception) {
            println(e.message)
            val error = when (e) {
                is InterruptedIOException -> ErrorGeneric(408, e.message.orEmpty())
                is JsonDataException -> ErrorGeneric(0, e.message.orEmpty())
                is IOException -> e
                else -> e
            }
            Result.failure(error)
        }
    }
}
