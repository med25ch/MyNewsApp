package com.example.mynewsapp.screens.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

@Composable
fun CategoriesScreen() {
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Categories Screen",
            fontWeight = FontWeight.SemiBold ,
            modifier = Modifier.clickable {  }
        )
    }
}