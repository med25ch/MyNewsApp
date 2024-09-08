package com.example.mynewsapp.screens.topnews

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.mynewsapp.R
import com.example.mynewsapp.retrofit.Article
import com.example.mynewsapp.sharedui.IndeterminateCircularIndicator

@Composable
fun TopNews(
    showDetail: () -> Unit,
    modifier: Modifier = Modifier,
    topNewsViewModel: TopNewsViewModel
) {

    val context = LocalContext.current

    // read this article : https://medium.com/androiddevelopers/consuming-flows-safely-in-jetpack-compose-cde014d0d5a3
    // collectAsStateWithLifecycle vs collectAsState
    val articlesResults = topNewsViewModel.articlesResults.collectAsStateWithLifecycle()

    //Todo 5: add a Column with a fillMaxSize and set horizontalAlignment to center
    Column(
        modifier = modifier
            .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Todo 6:Add a Text with text as Top News and fontWeight od semi bold
        Text(
            text = "Most Read",
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier
                .align(Alignment.Start)
                .padding(start = 8.dp, top = 8.dp),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = modifier.height(16.dp))

        if (articlesResults.value.isLoading) {
            IndeterminateCircularIndicator(showLoading = true)
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            articlesResults.value.articlesResult?.let {
                items(it.articles) { article ->
                    //Todo 7: Use TopNewsItem as the UI and pass in the result from the items
                    TopNewsSmallItem(
                        article = article,
                        modifier = modifier,
                        onLongClickArticle = {
                            topNewsViewModel.saveArticleToDb(article)
                            Toast.makeText(
                                context,
                                "Article added to favorite articles",
                                Toast.LENGTH_SHORT
                            ).show()
                        }, onClickArticle = {
                            topNewsViewModel.saveTemporaryArticleToDb(article)
                            showDetail()
                        }
                    )
                }
            }
        }
    }
}


//@Composable
//fun TopNewsItem(article: Article, onClickArticle : () -> Unit) {
//    Card(modifier = Modifier
//        .clip(RoundedCornerShape(8.dp))
//        .clickable {
//            onClickArticle()
//        }) {
//
//        //Image(painter = painterResource(id = MockData.topNewsList[0].image), contentDescription ="",contentScale = ContentScale.FillBounds)
//        Column (Modifier.padding(12.dp)){
//
//            AsyncImage(
//                model = article.urlToImage,
//                contentDescription = "",
//                placeholder = painterResource(R.drawable.breaking_news),
//                error = painterResource(R.drawable.breaking_news),
//                contentScale = ContentScale.Fit,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
//            )
//            Spacer(modifier = Modifier.height(6.dp))
//            article.title?.let { Text(text = it, style = MaterialTheme.typography.titleLarge) }
//            Spacer(modifier = Modifier.height(4.dp))
//            article.publishedAt?.let { Text(text = it,style = MaterialTheme.typography.headlineMedium, fontSize = 12.sp) }
//        }
//    }
//}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopNewsSmallItem(
    article: Article,
    modifier: Modifier = Modifier,
    onClickArticle: () -> Unit,
    onLongClickArticle: () -> Unit,
    showDeleteIcon: Boolean = false
) {
    Card(modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .combinedClickable(
            onClick = { onClickArticle() },
            onLongClick = { onLongClickArticle() }
        ),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary

        )
    ) {

        //Image(painter = painterResource(id = MockData.topNewsList[0].image), contentDescription ="",contentScale = ContentScale.FillBounds)
        Row(
            modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            AsyncImage(
                model = article.urlToImage,
                contentDescription = "",
                placeholder = painterResource(R.drawable.breaking_news),
                error = painterResource(R.drawable.breaking_news),
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .size(74.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = modifier.size(8.dp))

            Column(modifier = modifier.fillMaxWidth()) {
                Spacer(modifier = modifier.size(8.dp))
                article.title?.let { Text(text = it, style = MaterialTheme.typography.titleLarge) }
                Spacer(modifier = modifier.height(6.dp))

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    article.publishedAt?.let {
                        Text(
                            text = it, style = MaterialTheme.typography.headlineMedium,
                            fontSize = 12.sp,
                            modifier = modifier.align(Alignment.CenterVertically)
                        )
                    }

                    Icon(
                        imageVector = if (showDeleteIcon) Icons.Rounded.DeleteForever else Icons.Rounded.AddCircle,
                        contentDescription = "",
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                            .clickable {
                                onLongClickArticle()
                            }
                    )

                }

            }


        }
    }
}


@Preview
@Composable
fun Preview_TopNewsItem() {
    val article = Article(
        null,
        null,
        "bla bla bla bla dede hhhhhhhh hhhhhhhhhhhh",
        "hadi trumps bla bla",
        "",
        "",
        "March 5,01:01",
        ""
    )
    TopNewsSmallItem(
        article = article,
        onClickArticle = {},
        onLongClickArticle = {}
    )
}
