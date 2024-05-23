package com.example.mynewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynewsapp.navigation.AppNavGraph
import com.example.mynewsapp.navigation.RootScreen
import com.example.mynewsapp.screens.topnews.BottomNavigationBar
import com.example.mynewsapp.ui.theme.MyNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsApp(navController = rememberNavController())
                }
            }
        }
    }
}


@Composable
fun NewsApp(navController: NavHostController){

    val currentSelectedScreen by navController.currentScreenAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController,currentSelectedScreen = currentSelectedScreen)
        }
    ) { innerPadding ->
        AppNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyNewsAppTheme {
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<RootScreen> {
    val selectedItem = remember { mutableStateOf<RootScreen>(RootScreen.Home) }
    DisposableEffect(key1 = this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == RootScreen.Home.route } -> {
                    selectedItem.value = RootScreen.Home
                }
                destination.hierarchy.any { it.route == RootScreen.Categories.route } -> {
                    selectedItem.value = RootScreen.Categories
                }
                destination.hierarchy.any { it.route == RootScreen.Favorites.route } -> {
                    selectedItem.value = RootScreen.Favorites
                }
            }

        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}