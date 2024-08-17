package com.example.mynewsapp.screens.discoverScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mynewsapp.screens.topnews.TopNewsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverCategoriesScreen(
    showDetail: () -> Unit,
    modifier: Modifier = Modifier,
    discoverScreenViewModel: DiscoverScreenViewModel
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),horizontalAlignment = Alignment.Start) {

        Text(text = "Discover",
            fontWeight = FontWeight.ExtraBold ,
            fontSize = 25.sp,
            modifier = Modifier.clickable {  }
        )


        Text(text = "News from all over the world",
            fontWeight = FontWeight.Normal ,
            fontSize = 15.sp,
            modifier = Modifier.clickable {  }
        )

        SearchBarUI(modifier)
        
        Spacer(modifier = Modifier.size(15.dp))
        
        // Pager with categories
        NewsCategoriesTabRow(
            modifier,
            discoverScreenViewModel
        )


    }




}

@Preview
@Composable
fun CategoriesScreenPreview(){
    MaterialTheme {
        Surface {
            //DiscoverCategoriesScreen()
        }
    }
}


