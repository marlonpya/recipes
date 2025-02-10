
package com.arteaga.marlon.data

import com.arteaga.marlon.data.entities.RecipeEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

class ApiServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testFetchRecipesSuccess() {

        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, RecipeEntity::class.java)
        val adapter = moshi.adapter<List<RecipeEntity>>(listType)
        val jsonFile = File("src/test/kotlin/com/arteaga/marlon/data/fake/response.json")
        val recipes: List<RecipeEntity> = adapter.fromJson(jsonFile.readText()) ?: emptyList()

        mockWebServer.enqueue(MockResponse().setBody(adapter.toJson(recipes)))

        runTest {
            val response = apiService.fetchRecipes()
            assertTrue(response.isSuccessful)
            val result = response.body() ?: emptyList()
            assertNotNull(recipes)
            assertTrue(result.isNotEmpty())
        }
    }
}
