package com.example.mynewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mynewsapp.screens.categories.CategoriesScreen
import com.example.mynewsapp.screens.detailscreen.DetailScreen
import com.example.mynewsapp.screens.profile.ProfileScreen
import com.example.mynewsapp.screens.topnews.TopNews
import com.example.mynewsapp.screens.topnews.TopNewsViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = BottomNavItem.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(
            route = NavigationItem.DetailScreen.route + "/{articleId}",
            arguments = listOf(navArgument("articleId") { type = NavType.StringType }))
        {
                val id = it.arguments?.getString("articleId")
            if (id != null) {
                DetailScreen(navController,id)
            }
        }

        composable(BottomNavItem.Categories.route) {
            CategoriesScreen(navController)
        }

        composable(BottomNavItem.Profile.route) {
            ProfileScreen(navController)
        }

        composable(BottomNavItem.Home.route) {
            val viewModel = hiltViewModel<TopNewsViewModel>()
            TopNews(navController,viewModel)
        }
    }
}