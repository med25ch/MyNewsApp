package com.example.mynewsapp.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mynewsapp.screens.topnews.TopNewsSmallItem
import com.example.mynewsapp.sharedui.IndeterminateCircularIndicator

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    favoritesScreenViewModel: FavoritesScreenViewModel
) {

    // Check if we are cancel coroutines when we switch from tab to tab
    val favoritesArticlesUiState = favoritesScreenViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),horizontalAlignment = Alignment.Start) {

        Text(text = "Favorites",
            fontWeight = FontWeight.ExtraBold ,
            fontSize = 25.sp,
            modifier = Modifier.clickable {  },
            color = MaterialTheme.colorScheme.primary
        )

        Text(text = "Re read your articles anytime !",
            fontWeight = FontWeight.Normal ,
            fontSize = 15.sp,
            modifier = Modifier.clickable {  },
            color = MaterialTheme.colorScheme.primary
        )


        Spacer(modifier = Modifier.size(15.dp))

        // Pager with categories

        if (favoritesArticlesUiState.value.isLoading){

            IndeterminateCircularIndicator(showLoading = true)

        }else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ){
                items(favoritesArticlesUiState.value.favoritesArticlesList){ article->
                    //Todo 7: Use TopNewsItem as the UI and pass in the result from the items
                    TopNewsSmallItem(
                        article = article,
                        modifier = modifier,
                        onLongClickArticle = { favoritesScreenViewModel.deleteFavoriteArticle(article) },
                        onClickArticle = {
                            //topNewsViewModel.saveArticleToDb(article)
                            //showDetail()
                        }
                    )
                }
            }
        }

}
}