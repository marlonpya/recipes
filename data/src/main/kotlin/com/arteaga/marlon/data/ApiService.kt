
package com.arteaga.marlon.data

import com.arteaga.marlon.data.entities.RecipeEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(BuildConfig.get_recipes)
    suspend fun fetchRecipes(): Response<List<RecipeEntity>>
}
