
package com.arteaga.marlon.domain.usecase

import com.arteaga.marlon.domain.models.ErrorGeneric
import com.arteaga.marlon.domain.models.Recipe
import com.arteaga.marlon.domain.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetRecipesUseCaseTest {

    private val repository: RecipeRepository = mockk()

    private lateinit var useCase: GetRecipesUseCase

    @Before
    fun setup() {
        useCase = GetRecipesUseCase(
            repository, Dispatchers.Unconfined,
        )
    }

    @Test
    fun `invoke should return success result with data`() = runTest {
        val mockRecipes = listOf<Recipe>()
        coEvery { repository.fetchRecipes() } returns Result.success(mockRecipes)

        val result = useCase()

        assertTrue(result.isSuccess)
        assertEquals(mockRecipes, result.getOrNull())
    }

    @Test
    fun `invoke should return failure result on error`() = runTest {
        val error = ErrorGeneric(404, "Not Found")
        coEvery { repository.fetchRecipes() } returns Result.failure(error)

        val result = useCase()

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    @Test
    fun `invoke should return failure result on repository error`() = runTest {
        val error = ErrorGeneric(404, "Not Found")
        coEvery {
            withContext(useCase.dispatcher) {
                repository.fetchRecipes()
            }
        } returns Result.failure(error)

        val result = useCase()

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    @Test
    fun `invoke should return failure result on context exception`() = runTest {

        coEvery { repository.fetchRecipes() } coAnswers {
            withContext(useCase.dispatcher) {
                Result.failure(Exception("Error"))
            }
        }

        val result = useCase()

        assertTrue(result.isFailure)
    }
}
