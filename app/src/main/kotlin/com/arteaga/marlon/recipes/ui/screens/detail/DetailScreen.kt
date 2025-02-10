
package com.arteaga.marlon.recipes.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.core.util.Function
import com.arteaga.marlon.domain.models.Recipe

@Composable
fun DetailScreen(
    recipe: Recipe?,
    goToMaps: Function<Recipe?, Unit>,
) {

    ErrorState(recipe == null)

    SuccessState(recipe != null, recipe, goToMaps)
}
