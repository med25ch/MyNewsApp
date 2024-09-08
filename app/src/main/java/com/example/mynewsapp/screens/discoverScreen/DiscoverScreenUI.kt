package com.example.mynewsapp.screens.discoverScreen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mynewsapp.repositories.CATEGORY
import com.example.mynewsapp.screens.discoverScreen.Items.tabItem
import com.example.mynewsapp.screens.topnews.TopNewsSmallItem
import com.example.mynewsapp.sharedui.IndeterminateCircularIndicator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarUI(modifier: Modifier = Modifier){
    // this is the text users enter
    var queryString by remember {
        mutableStateOf("")
    }

    // if the search bar is active or not
    var isActive by remember {
        mutableStateOf(false)
    }

    val contextForToast = LocalContext.current.applicationContext

    //Update the search query
    //homeScreenViewModel.updateSearchQuery(queryString)

    val onActiveChange: (Boolean) -> Unit = { activeChange ->
        isActive = activeChange
    }

    SearchBar(
        modifier = modifier,
        inputField = {
            SearchBarDefaults.InputField(
                modifier = modifier,
                query = queryString,
                onQueryChange = { newQueryString ->
                    queryString = newQueryString
                },
                onSearch = {
                    isActive = false
                    Toast.makeText(contextForToast, "Your query string: $queryString", Toast.LENGTH_SHORT)
                        .show()
                },
                expanded = false,
                onExpandedChange = onActiveChange,
                placeholder = {
                    Text(
                        text = "Search",
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
            )
        },
        expanded = false,
        onExpandedChange = onActiveChange,
        shape = RoundedCornerShape(8.dp),
        content = {},
    )
}

@Preview
@Composable
fun SearchBarPreview(){
    SearchBarUI()
}


// Function to handle Object.
@ExperimentalFoundationApi
@Composable
fun NewsCategoriesTabRow(
    modifier: Modifier = Modifier,
    discoverScreenViewModel : DiscoverScreenViewModel
    ) {

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        tabItem.size
    }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItem.forEachIndexed { index, tabItem ->

                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                        discoverScreenViewModel.fetchArticlesByCategory(category = CATEGORY.entries[selectedTabIndex])
                    },
                    icon = {
                        androidx.compose.material.Icon(
                            imageVector = if (index == selectedTabIndex) {
                                tabItem.selectedIcon
                            } else tabItem.unSelectedItem,
                            contentDescription = tabItem.title,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                )
            }
        }

        // Check if we are cancel coroutines when we switch from tab to tab
        val articlesUiState = discoverScreenViewModel.articlesResults.collectAsStateWithLifecycle()

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->

            Box(modifier = Modifier.fillMaxSize()) {

                Column(modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 4.dp).fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {

                    Spacer(modifier = Modifier.height(16.dp))

                    //Todo 6:Add a Text with text as Top News and fontWeight od semi bold
                    Text(text = tabItem[index].title + " News",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 8.dp, top = 8.dp))

                    Spacer(modifier = Modifier.height(16.dp))


                    if (articlesUiState.value.isLoading){

                        IndeterminateCircularIndicator(showLoading = true)

                    }else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ){
                            articlesUiState.value.articlesResult?.let {
                                items(it.articles){ article->
                                    //Todo 7: Use TopNewsItem as the UI and pass in the result from the items
                                    TopNewsSmallItem(
                                        article = article,
                                        modifier = modifier,
                                        onLongClickArticle = {},
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
            }
        }
    }
}

