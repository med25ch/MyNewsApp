package com.example.mynewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mynewsapp.screens.detailscreen.DetailScreen
import com.example.mynewsapp.screens.topnews.TopNews

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.TopNewsScreen.route,

) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.DetailScreen.route) {
            DetailScreen(navController)
        }
        composable(NavigationItem.TopNewsScreen.route) {
            TopNews(navController)
        }
    }
}