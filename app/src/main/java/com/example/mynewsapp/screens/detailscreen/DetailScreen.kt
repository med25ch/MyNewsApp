package com.example.mynewsapp.screens.detailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navController: NavHostController,
    detailScreenViewModel: DetailScreenViewModel
) {

    val uiState = detailScreenViewModel.uiState.collectAsStateWithLifecycle()


    DisposableEffect(LocalLifecycleOwner.current) {
        onDispose {
            detailScreenViewModel.deleteAllArticles()
        }
    }

    if (uiState.value.isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

    } else {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = uiState.value.articleEntity.title,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }
    }




}