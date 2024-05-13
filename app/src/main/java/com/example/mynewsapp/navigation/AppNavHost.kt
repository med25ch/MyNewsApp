package com.example.mynewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mynewsapp.screens.detailscreen.DetailScreen
import com.example.mynewsapp.screens.topnews.TopNews

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.TopNewsScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.TopNewsScreen.route) {
            TopNews(navController)
        }

        composable(
            route = NavigationItem.DetailScreen.route + "/{articleId}",
            arguments = listOf(navArgument("articleId") { type = NavType.IntType }))
        {
                val id = it.arguments?.getInt("articleId")
            if (id != null) {
                DetailScreen(navController,id)
            }
        }
    }
}