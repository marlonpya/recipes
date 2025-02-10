
package com.arteaga.marlon.recipes.navigation

sealed class NavigationHost(val route: String) {
    object InitNavHost : NavigationHost("navigation_host")
}
