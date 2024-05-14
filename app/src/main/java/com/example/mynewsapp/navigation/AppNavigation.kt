package com.example.mynewsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    DETAIL_SCREEN,
    TOP_NEWS_SCREEN,
}
sealed class NavigationItem(val route: String) {
    data object TopNewsScreen : NavigationItem(Screen.TOP_NEWS_SCREEN.name)
    data object DetailScreen : NavigationItem(Screen.DETAIL_SCREEN.name)
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    data object Categories : BottomNavItem("search", Icons.Default.Search, "Search")
    data object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}


val items = listOf(
    BottomNavItem.Home,
    BottomNavItem.Categories,
    BottomNavItem.Profile
)