package com.example.mynewsapp.screens.discoverScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.repositories.CATEGORY
import com.example.mynewsapp.repositories.COUNTRY
import com.example.mynewsapp.repositories.NewsArticlesRepo
import com.example.mynewsapp.retrofit.Article
import com.example.mynewsapp.retrofit.ArticlesResult
import com.example.mynewsapp.retrofit.toArticleEntity
import com.example.mynewsapp.room.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class DiscoverScreenViewModel @Inject constructor(
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
        fetchArticlesByCategory(category = CATEGORY.Business)
    }

    fun fetchArticlesByCategory(country: COUNTRY = COUNTRY.US, category: CATEGORY) {

        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            try {
                Log.i("DiscoverScreenViewModel", "fetchArticlesByCategory Coroutine for $category")

                newsArticlesRepo.getTopHeadlinesByCategory(country, category)
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        Log.e(
                            "fetchArticlesByCategory",
                            "Error ${e.message} | Cause : ${e.cause}"
                        )
                    }
                    .collect {
                        _articlesResults.value = it
                    }
            } catch (e: CancellationException) {
                Log.i("DiscoverScreenViewModel", "previous job canceled for $category")
            }
        }
    }

    fun saveArticleToDb(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            val articleEntity = article.toArticleEntity()
            articlesRoomRepository.insert(articleEntity)
        }
    }
}