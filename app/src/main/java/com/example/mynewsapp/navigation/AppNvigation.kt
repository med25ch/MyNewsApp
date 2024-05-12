package com.example.mynewsapp.navigation

enum class Screen {
    DETAIL_SCREEN,
    TOP_NEWS_SCREEN,
}
sealed class NavigationItem(val route: String) {
    object TopNewsScreen : NavigationItem(Screen.TOP_NEWS_SCREEN.name)
    object DetailScreen : NavigationItem(Screen.DETAIL_SCREEN.name)
}