
package com.arteaga.marlon.recipes

import com.arteaga.marlon.domain.models.Recipe
import com.arteaga.marlon.recipes.ui.state.UiState
import com.arteaga.marlon.recipes.ui.state.UiStateHolder

data class RecipeData(
    val getData: () -> Unit = {},
    val recipes: List<Recipe> = emptyList(),
    val selectedRecipe: Recipe? = null,
    override var currentState: UiState = UiState.INIT,
) : UiStateHolder
