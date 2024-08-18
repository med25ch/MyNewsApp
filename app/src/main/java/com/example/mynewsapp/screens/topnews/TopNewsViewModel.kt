package com.example.mynewsapp.screens.topnews

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.repositories.COUNTRY
import com.example.mynewsapp.repositories.NewsArticlesRepo
import com.example.mynewsapp.retrofit.Article
import com.example.mynewsapp.retrofit.ArticlesResult
import com.example.mynewsapp.retrofit.INewsApi
import com.example.mynewsapp.retrofit.toArticleEntity
import com.example.mynewsapp.room.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel

class TopNewsViewModel @Inject constructor(
    private val newsArticlesRepo: NewsArticlesRepo,
    private val articlesRoomRepository: Repository
) : ViewModel() {

    private var searchJob: Job? = null

    private val _articlesResults = MutableStateFlow(ArticlesResult())
    val articlesResults: StateFlow<ArticlesResult>
        get() = _articlesResults

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    init {
        fetchArticles()
    }

    private fun fetchArticles() {

        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            try {
                Log.i("TopNewsViewModel", "fetchArticles")

                newsArticlesRepo.getTopHeadlines(COUNTRY.US)
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        Log.e(
                            "fetchArticles",
                            "Error ${e.message} | Cause : ${e.cause}"
                        )
                    }
                    .collect {
                        _articlesResults.value = it
                    }

            } catch (e: CancellationException) {
                Log.i("TopNewsViewModel", "previous job canceled for fetchArticles")
            }
        }
    }

    fun saveArticleToDb(article: Article) {
        viewModelScope.launch(IO) {
            val articleEntity = article.toArticleEntity()
            articlesRoomRepository.insert(articleEntity)
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