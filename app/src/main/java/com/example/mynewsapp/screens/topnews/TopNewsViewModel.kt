package com.example.mynewsapp.screens.topnews

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.repositories.COUNTRY
import com.example.mynewsapp.repositories.NewsArticlesRepo
import com.example.mynewsapp.retrofit.Article
import com.example.mynewsapp.retrofit.ArticlesResult
import com.example.mynewsapp.retrofit.toArticleEntity
import com.example.mynewsapp.room.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel

class TopNewsViewModel @Inject constructor(
    private val newsArticlesRepo: NewsArticlesRepo,
    private val articlesRoomRepository: Repository
) : ViewModel() {

    private var searchJob: Job? = null

    val articlesResults: StateFlow<NewsUiState>
        get() = _newsUiState

    private val _newsUiState = MutableStateFlow(NewsUiState())

    init {
        fetchArticles()
    }

    private fun fetchArticles() {

        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            _newsUiState.value = _newsUiState.value.copy(isLoading = true)

            try {
                Log.i("TopNewsViewModel", "fetchArticles")

                newsArticlesRepo.getTopHeadlines(COUNTRY.US)
                    .flowOn(IO)
                    .catch { e ->
                        Log.e(
                            "fetchArticles",
                            "Error ${e.message} | Cause : ${e.cause}"
                        )
                    }
                    .collect {
                        _newsUiState.value.articlesResult = it
                    }
            } catch (e: CancellationException) {
                Log.i("TopNewsViewModel", "previous job canceled for fetchArticles")
            } finally {
                _newsUiState.value = _newsUiState.value.copy(isLoading = false)
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

data class NewsUiState(
    var articlesResult: ArticlesResult ?= null,
    var isLoading : Boolean = false)