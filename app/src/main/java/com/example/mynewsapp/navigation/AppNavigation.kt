package com.example.mynewsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    DETAIL_SCREEN,
    HOME_SCREEN,
    CATEGORIES_SCREEN,
    FAVORITES
}
sealed class LeafScreen(val route: String) {
    data object Home : LeafScreen(Screen.HOME_SCREEN.name)
    data object DetailScreens : LeafScreen(Screen.DETAIL_SCREEN.name)
    data object Categories : LeafScreen(Screen.CATEGORIES_SCREEN.name)
    data object Favorites : LeafScreen(Screen.FAVORITES.name)
}

sealed class RootScreen(val route: String, val icon: ImageVector, val label: String) {
    data object Home : RootScreen("home_root", Icons.Default.Home, "Home")
    data object Categories : RootScreen("categories_root", Icons.Default.Search, "Discover")
    data object Favorites : RootScreen("favorites_root", Icons.Default.Favorite, "Favorites")
}


val items = listOf(
    RootScreen.Home,
    RootScreen.Categories,
    RootScreen.Favorites
)