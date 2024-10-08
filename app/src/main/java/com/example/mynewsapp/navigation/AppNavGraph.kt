package com.example.mynewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mynewsapp.screens.discoverScreen.DiscoverCategoriesScreen
import com.example.mynewsapp.screens.detailscreen.DetailScreen
import com.example.mynewsapp.screens.detailscreen.DetailScreenViewModel
import com.example.mynewsapp.screens.discoverScreen.DiscoverScreenViewModel
import com.example.mynewsapp.screens.favorites.FavoritesScreen
import com.example.mynewsapp.screens.favorites.FavoritesScreenViewModel
import com.example.mynewsapp.screens.topnews.TopNews
import com.example.mynewsapp.screens.topnews.TopNewsViewModel

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = RootScreen.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

//        composable(RootScreen.Favorites.route) {
//            FavoritesScreen()
//        }

        //Nested Navigation implementation
        addHomeRoute(navController)
        addCategoriesRoute(navController)
        addFavoritesRoute(navController)

    }

}

private fun NavGraphBuilder.addHomeRoute(navController: NavController) {
    navigation(
        route = RootScreen.Home.route,
        startDestination = LeafScreen.Home.route
    ) {
        showHome(navController)
        showHomeDetail(navController)
    }
}

//home navigation
private fun NavGraphBuilder.showHome(navController: NavController) {
    composable(route = LeafScreen.Home.route) {
        val viewModel = hiltViewModel<TopNewsViewModel>()
        TopNews(
            showDetail = {
                navController.navigate(LeafScreen.DetailScreens.route)
            },
            topNewsViewModel = viewModel
        )
    }
}
private fun NavGraphBuilder.showHomeDetail(navController: NavController) {
    composable(route = LeafScreen.DetailScreens.route) {
        val viewModel = hiltViewModel<DetailScreenViewModel>()
        DetailScreen(
            onBack = {
                navController.navigateUp()
            },
            detailScreenViewModel = viewModel
        )
    }
}

// end of home navigation


//search navigation
private fun NavGraphBuilder.addCategoriesRoute(navController: NavController) {
    navigation(
        route = RootScreen.Categories.route,
        startDestination = LeafScreen.Categories.route
    ) {
        showCategories(navController)
    }
}
private fun NavGraphBuilder.showCategories(navController: NavController) {
    composable(route = LeafScreen.Categories.route) {
        val viewModel = hiltViewModel<DiscoverScreenViewModel>()
        DiscoverCategoriesScreen(
            showDetail = {},
            discoverScreenViewModel = viewModel
        )
    }
}
//end of search navigation


//favorites navigation
private fun NavGraphBuilder.addFavoritesRoute(navController: NavController) {
    navigation(
        route = RootScreen.Favorites.route,
        startDestination = LeafScreen.Favorites.route
    ) {
        showFavorites(navController)
    }
}
private fun NavGraphBuilder.showFavorites(navController: NavController) {
    composable(route = LeafScreen.Favorites.route) {
        val viewModel = hiltViewModel<FavoritesScreenViewModel>()
        FavoritesScreen(favoritesScreenViewModel = viewModel)
    }
}
//end of favorites navigation


// inspiration :

//https://dribbble.com/shots/15193792-News-iOS-mobile-app