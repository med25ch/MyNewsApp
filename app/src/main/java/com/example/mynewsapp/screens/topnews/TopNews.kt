package com.example.mynewsapp.screens.topnews

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mynewsapp.R
import com.example.mynewsapp.mock.MockData
import com.example.mynewsapp.navigation.NavigationItem
import com.example.mynewsapp.retrofit.Article

@Composable
fun TopNews(navController: NavHostController,
            topNewsViewModel: TopNewsViewModel) {

    // read this article : https://medium.com/androiddevelopers/consuming-flows-safely-in-jetpack-compose-cde014d0d5a3
    // collectAsStateWithLifecycle vs collectAsState
    val articlesResults = topNewsViewModel.articlesResults.collectAsStateWithLifecycle()

    //Todo 5: add a Column with a fillMaxSize and set horizontalAlignment to center
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        //Todo 6:Add a Text with text as Top News and fontWeight od semi bold
        Text(text = "Most Read",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 8.dp, top = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            items(articlesResults.value.articles.filter { it.urlToImage != null }){ article->
                //Todo 7: Use TopNewsItem as the UI and pass in the result from the items
                TopNewsItem(
                    article = article,
                    onClickArticle = {
                        topNewsViewModel.saveArticleToDb(article)
                        navController.navigate(NavigationItem.DetailScreen.route)
                    }
                )
            }
        }
    }
}


@Composable
fun TopNewsItem(article: Article, onClickArticle : () -> Unit) {
    Card(modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .clickable {
            onClickArticle()
        }) {

        //Image(painter = painterResource(id = MockData.topNewsList[0].image), contentDescription ="",contentScale = ContentScale.FillBounds)
        Column (Modifier.padding(12.dp)){

            AsyncImage(
                model = article.urlToImage,
                contentDescription = "",
                placeholder = painterResource(R.drawable.breaking_news),
                error = painterResource(R.drawable.breaking_news),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(6.dp))
            article.title?.let { Text(text = it,color = Color.White, style = MaterialTheme.typography.titleLarge) }
            Spacer(modifier = Modifier.height(2.dp))
            article.publishedAt?.let { Text(text = it,color = Color.White,style = MaterialTheme.typography.labelSmall) }
        }
    }
}

@Preview
@Composable
fun Preview_TopNewsItem(){
    val article = Article(null, null, "bla bla bla bla", "hadi trumps bla bla", "", "", "March 5,01:01", "")
    TopNewsItem(
        article = article,
        onClickArticle = {}
    )
}
