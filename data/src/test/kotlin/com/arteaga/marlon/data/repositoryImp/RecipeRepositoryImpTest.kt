
package com.arteaga.marlon.data.repositoryImp

import com.arteaga.marlon.data.ApiService
import com.arteaga.marlon.data.entities.RecipeEntity
import com.arteaga.marlon.data.fake.RecipeEntityTestData
import com.arteaga.marlon.data.mapper.toModel
import com.arteaga.marlon.data.utils.handleApiResponse
import com.arteaga.marlon.domain.models.ErrorGeneric
import com.arteaga.marlon.domain.models.Recipe
import com.squareup.moshi.JsonDataException
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.InterruptedIOException

class RecipeRepositoryImpTest {

    private lateinit var repository: RecipeRepositoryImp
    private val apiService = mockk<ApiService>()

    @Before
    fun setUp() {
        repository = RecipeRepositoryImp(apiService)
    }

    @Test
    fun `fetchRecipes success`() = runTest {
        val response = Response.success(listOf(RecipeEntityTestData.createRecipeEntity()))
        coEvery { apiService.fetchRecipes() } returns response

        val result = repository.fetchRecipes()

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
    }

    @Test
    fun `fetchRecipes error`() = runTest {
        val response = Response.error<List<RecipeEntity>>(404, "".toResponseBody(null))
        coEvery { apiService.fetchRecipes() } returns response

        val result = repository.fetchRecipes()

        assertTrue(result.isFailure)
        assertEquals(404, (result.exceptionOrNull() as ErrorGeneric).code)
    }

    @Test
    fun `handleApiResponse success`() = runTest {
        coEvery { apiService.fetchRecipes() } coAnswers {
            val response = Response.success(listOf(RecipeEntityTestData.createRecipeEntity()))
            response
        }

        val result = handleApiResponse(
            apiCall = { apiService.fetchRecipes() },
            transform = { entities -> entities.map { it.toModel() } }
        )

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
    }

    @Test
    fun `handleApiResponse error`() = runTest {
        coEvery { apiService.fetchRecipes() } coAnswers {
            val response = Response.error<List<RecipeEntity>>(404, "".toResponseBody(null))
            response
        }

        val result = handleApiResponse(
            apiCall = { apiService.fetchRecipes() },
            transform = { entities -> entities.map { it.toModel() } }
        )

        assertTrue(result.isFailure)
        assertEquals(404, (result.exceptionOrNull() as ErrorGeneric).code)
    }

    @Test
    fun `handleApiResponse network error`() = runTest {
        val responseSlot = slot<suspend () -> Response<List<RecipeEntity>>>()
        coEvery { apiService.fetchRecipes() } coAnswers {
            responseSlot.captured.invoke()
            throw InterruptedIOException("Connection timed out")
        }

        val result = handleApiResponse(
            apiCall = { apiService.fetchRecipes() },
            transform = { entities -> entities.map { it.toModel() } }
        )

        assertTrue(result.isFailure)
        assert(result.exceptionOrNull() is Throwable)
    }

    @Test
    fun `handleApiResponse data error`() = runTest {
        val responseSlot = slot<suspend () -> Response<List<RecipeEntity>>>()
        coEvery { apiService.fetchRecipes() } coAnswers {
            responseSlot.captured()
            throw JsonDataException(RecipeEntityTestData.recipeJsonError)
        }

        val result = handleApiResponse(
            apiCall = { apiService.fetchRecipes() },
            transform = { entities -> entities.map { it.toModel() } }
        )

        assertTrue(result.isFailure)
        assert(result.exceptionOrNull() is Throwable)
    }

    @Test
    fun isSuccess() = runTest {
        val repository = RecipeRepositoryImp(FakeSuccessApiService())
        val result: Result<List<Recipe>> = repository.fetchRecipes()
        assert(result.isSuccess)
    }

    @Test
    fun isNoEmpty() = runTest {
        val dataFake = RecipeMock.dataTake
        val dataConverted = dataFake.map { it.toModel() }
        assert(dataConverted.isNotEmpty())
    }

    @Test
    fun isError() = runTest {
        val repository = RecipeRepositoryImp(FakeErrorApiService())
        val result: Result<List<Recipe>> = repository.fetchRecipes()
        assert(result.isFailure)
    }
}

class FakeSuccessApiService : ApiService {
    override suspend fun fetchRecipes(): Response<List<RecipeEntity>> {
        return Response.success(emptyList())
    }
}

class FakeErrorApiService : ApiService {
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override suspend fun fetchRecipes(): Response<List<RecipeEntity>> {
        return Response.error(401, null)
    }
}
