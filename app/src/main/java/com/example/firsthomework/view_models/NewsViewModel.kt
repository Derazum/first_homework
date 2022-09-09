package com.example.firsthomework.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firsthomework.services.entities.News
import com.example.firsthomework.services.repositories.NewsRepository
import com.example.firsthomework.services.utils.doOnError
import com.example.firsthomework.services.utils.doOnSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Loading)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    fun refresh() {
        getNewData()
    }

    private fun getData() {
        viewModelScope.launch {
            _state.emit(NewsState.Loading)
            repository.getNews().collect {
                it.doOnSuccess {
                        news -> _state.emit(NewsState.Content(news = news))
                }
                it.doOnError {
                        error -> _state.emit(NewsState.Error(error))
                }
            }
        }
    }

    private fun getNewData() {
        viewModelScope.launch {
            _state.emit(NewsState.Loading)
            repository.getNewNews().collect {
                it.doOnSuccess {
                        news -> _state.emit(NewsState.Content(news = news))
                }
                it.doOnError {
                        error -> _state.emit(NewsState.Error(error))
                }
            }
        }
    }
}

sealed class NewsState {
    object Loading: NewsState()
    data class Error(val throwable: Throwable): NewsState()
    data class Content(val news: List<News>): NewsState()
}