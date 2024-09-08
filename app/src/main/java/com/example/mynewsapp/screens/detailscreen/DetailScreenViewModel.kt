package com.example.mynewsapp.screens.detailscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.room.Repository
import com.example.mynewsapp.room.TemporaryArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val articlesRoomRepository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenUiState())
    val uiState = _uiState.asStateFlow()


    init {
        getTemporaryArticle()
    }

    private fun getTemporaryArticle() = viewModelScope.launch {
        articlesRoomRepository.getTemporaryArticle()
            .flowOn(IO)
            .catch { e ->
                Log.e(
                    "getTemporaryArticle",
                    "Error ${e.message} | Cause : ${e.cause}"
                )
            }
            .collect {
                try {

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        temporaryArticleEntity = it
                    )
                }
                catch (e :Exception){
                    Log.e(
                        "getTemporaryArticle",
                        "Error ${e.message} | Cause : ${e.cause}"
                    )

                    //Default value in case db return null object.
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        temporaryArticleEntity = TemporaryArticleEntity()
                    )
                }
        }
    }


    fun deleteTemporaryArticle(){
        viewModelScope.launch{
            articlesRoomRepository.deleteAllTemporaryArticle()
        }
    }

}

data class DetailScreenUiState(
    val isLoading : Boolean = true,
    val temporaryArticleEntity: TemporaryArticleEntity = TemporaryArticleEntity(),
)