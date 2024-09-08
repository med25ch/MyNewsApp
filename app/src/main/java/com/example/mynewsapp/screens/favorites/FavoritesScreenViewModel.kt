package com.example.mynewsapp.screens.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.retrofit.Article
import com.example.mynewsapp.retrofit.toArticle
import com.example.mynewsapp.retrofit.toArticleEntity
import com.example.mynewsapp.room.ArticleEntity
import com.example.mynewsapp.room.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val articlesRoomRepository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesScreenUiState())
    val uiState = _uiState.asStateFlow()


    init {
        getAllFavoritesArticle()
    }

    private fun getAllFavoritesArticle() = viewModelScope.launch {
        articlesRoomRepository.getAllFavoriteArticles()
            .flowOn(IO)
            .catch { e ->
                Log.e(
                    "getAllFavoritesArticle",
                    "Error ${e.message} | Cause : ${e.cause}"
                )
            }.map { articleEntityList ->
                articleEntityList.map { it.toArticle() }
            }
            .collect {

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        favoritesArticlesList = it
                    )
            }
    }


    fun deleteFavoriteArticle(article: Article){
        viewModelScope.launch{
            val articleEntity = article.toArticleEntity()
            articlesRoomRepository.delete(articleEntity)
        }
    }

    fun deleteAllFavoritesArticle(){
        viewModelScope.launch{
            articlesRoomRepository.deleteAllArticle()
        }
    }

}

data class FavoritesScreenUiState(
    val isLoading : Boolean = true,
    val favoritesArticlesList: List<Article> = listOf(),
)