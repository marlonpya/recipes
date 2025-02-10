
package com.arteaga.marlon.recipes.navigation

sealed class NavigationScreen(val screen: String) {
    object HomeScreen : NavigationScreen("home_screen")
    object DetailScreen : NavigationScreen("detail_screen")
    object MapsScreen : NavigationScreen("maps_screen")
}
