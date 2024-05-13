package com.example.mynewsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mynewsapp.navigation.AppNavHost
import com.example.mynewsapp.retrofit.INewsApi
import com.example.mynewsapp.retrofit.RetrofitHelper
import com.example.mynewsapp.ui.theme.MyNewsAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
                    AppNavHost(navController = rememberNavController())

//                    val articlesApi = RetrofitHelper.getInstance().create(INewsApi::class.java)
//
//                    GlobalScope.launch {
//                        val result = articlesApi.getTopHeadlines()
//                        if (result != null){
//                            Log.d("article result",result.body().toString())
//                        }
//                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyNewsAppTheme {
    }
}