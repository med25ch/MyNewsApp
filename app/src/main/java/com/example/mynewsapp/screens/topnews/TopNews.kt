package com.example.mynewsapp.screens.topnews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mynewsapp.mock.MockData
import com.example.mynewsapp.models.NewsData
import com.example.mynewsapp.navigation.NavigationItem

@Composable
fun TopNews(navController: NavHostController) {
    //Todo 5: add a Column with a fillMaxSize and set horizontalAlignment to center
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        //Todo 6:Add a Text with text as Top News and fontWeight od semi bold
        Text(text = "Top News",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable { navController.navigate(NavigationItem.DetailScreen.route) })


        LazyColumn{
            items(MockData.topNewsList){ newsData->
                //Todo 7: Use TopNewsItem as the UI and pass in the result from the items
                TopNewsItem(newsData = newsData,
                    onClickArticle = { navController.navigate(NavigationItem.DetailScreen.route + "/${newsData.id}") }
                    )
            }
        }
    }
}


@Composable
fun TopNewsItem(newsData: NewsData,onClickArticle : () -> Unit) {
    Box(modifier = Modifier.height(200.dp).padding(8.dp).clickable {
        onClickArticle()
    }) {
        Image(painter = painterResource(id = newsData.image), contentDescription ="",contentScale = ContentScale.FillBounds)
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = newsData.publishedAt,color = Color.White,fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = newsData.title,color = Color.White,fontWeight = FontWeight.SemiBold)
        }
    }
}