package com.example.mynewsapp.sharedui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IndeterminateCircularIndicator(modifier: Modifier = Modifier,showLoading : Boolean = false){

    if (!showLoading) return

    Box(modifier = modifier.fillMaxSize().padding(32.dp), contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            modifier = modifier,
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}



@Preview
@Composable
fun Preview_TopNewsItem(){
    IndeterminateCircularIndicator(showLoading = true)
}