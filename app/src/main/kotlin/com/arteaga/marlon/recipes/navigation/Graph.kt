
package com.arteaga.marlon.recipes.navigation

import androidx.core.util.Function
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arteaga.marlon.domain.models.Recipe
import com.arteaga.marlon.recipes.RecipeData
import com.arteaga.marlon.recipes.ui.screens.detail.DetailScreen
import com.arteaga.marlon.recipes.ui.screens.home.HomeScreen
import com.arteaga.marlon.recipes.ui.screens.maps.MapsScreen

fun NavGraphBuilder.graph(
    state: () -> RecipeData,
    goToDetail: Function<Recipe?, Unit>,
    goToMaps: Function<Recipe?, Unit>,
) {
    navigation(
        startDestination = NavigationScreen.HomeScreen.screen,
        route = NavigationHost.InitNavHost.route
    ) {
        composable(NavigationScreen.HomeScreen.screen) {
            HomeScreen(state, goToDetail, state().getData)
        }
        composable(NavigationScreen.DetailScreen.screen) {
            DetailScreen(state().selectedRecipe, goToMaps)
        }
        composable(NavigationScreen.MapsScreen.screen) {
            MapsScreen(state().selectedRecipe)
        }
    }
}
