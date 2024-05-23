package com.example.mynewsapp.screens.topnews

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.mynewsapp.navigation.RootScreen
import com.example.mynewsapp.navigation.items


@Composable
fun BottomNavigationBar(navController: NavController,currentSelectedScreen: RootScreen) {
    BottomNavigation {
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentSelectedScreen == item,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigateToRootScreen(item)
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) }
            )
        }
    }
}

private fun NavController.navigateToRootScreen(rootScreen: RootScreen) {
    navigate(rootScreen.route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}
