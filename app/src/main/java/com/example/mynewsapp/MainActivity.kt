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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynewsapp.navigation.AppNavHost
import com.example.mynewsapp.repositories.NewsArticlesRepo
import com.example.mynewsapp.retrofit.ApiHelper
import com.example.mynewsapp.retrofit.INewsApi
import com.example.mynewsapp.screens.topnews.BottomNavigationBar
import com.example.mynewsapp.ui.theme.MyNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        AppNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyNewsAppTheme {
    }
}