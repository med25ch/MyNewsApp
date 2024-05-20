package com.example.mynewsapp.screens.topnews

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.repositories.COUNTRY
import com.example.mynewsapp.repositories.NewsArticlesRepo
import com.example.mynewsapp.retrofit.Article
import com.example.mynewsapp.retrofit.ArticlesResult
import com.example.mynewsapp.retrofit.INewsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopNewsViewModel @Inject constructor(
    private val newsArticlesRepo: NewsArticlesRepo,
): ViewModel() {


    private val _articlesResults = MutableStateFlow(ArticlesResult())
    val articlesResults: StateFlow<ArticlesResult>
        get() = _articlesResults

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    init {
        fetchArticles()
    }

    private fun fetchArticles(){
        viewModelScope.launch {
            newsArticlesRepo.getTopHeadlines(COUNTRY.US)
                .flowOn(Dispatchers.IO)
                .catch {e ->
                    Log.e("fetchArticles",
                        "Error ${e.message} | Cause : ${e.cause}")
                }
                .collect{
                    _articlesResults.value = it
                }
        }
    }



//    private fun fetchArticlesResults() {
//        viewModelScope.launch (Dispatchers.IO){
//            try {
//                _isLoading.value = true
//                _articlesResults.value = newsArticlesRepo.getArticlesByCountry(COUNTRY.US)
//                _isLoading.value = false
//            }catch (e : Exception){
//                Log.e("fetchArticlesResults", "Error fetching articles: ${e.message}")
//            }finally {
//                _isLoading.value = false
//            }
//        }
  //  }

}