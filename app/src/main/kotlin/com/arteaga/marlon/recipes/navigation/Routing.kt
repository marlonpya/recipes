
package com.arteaga.marlon.recipes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun Routing(
    navController: NavHostController,
    uiEvents: SharedFlow<UIEvents>,
) {
    LaunchedEffect(key1 = Unit) {
        uiEvents.collect {
            when (it) {
                UIEvents.GoDetail -> {
                    navController.navigate(NavigationScreen.DetailScreen.screen)
                }
                UIEvents.GoMaps -> {
                    navController.navigate(NavigationScreen.MapsScreen.screen)
                }
            }
        }
    }
}
