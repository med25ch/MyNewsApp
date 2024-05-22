package com.example.mynewsapp.screens.detailscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.room.ArticleEntity
import com.example.mynewsapp.room.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val articlesRoomRepository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenUiState())
    val uiState = _uiState.asStateFlow()


    init {
        getArticle()
    }

    private fun getArticle() {
        viewModelScope.launch {
            articlesRoomRepository.getAllFavoriteArticles()
                .flowOn(IO)
                .catch { e ->
                    Log.e(
                        "getArticle",
                        "Error ${e.message} | Cause : ${e.cause}"
                    )
                }
                .collect {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        articleEntity = it.last()
                    )
                }

        }
    }

    fun deleteAllArticles(){
        viewModelScope.launch{
            articlesRoomRepository.deleteAllArticle()
        }
    }

}

data class DetailScreenUiState(
    val isLoading : Boolean = true,
    val articleEntity: ArticleEntity = ArticleEntity(),
)