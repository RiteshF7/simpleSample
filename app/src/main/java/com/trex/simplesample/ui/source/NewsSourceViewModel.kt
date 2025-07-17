package com.trex.simplesample.ui.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trex.simplesample.domain.models.NewsSources
import com.trex.simplesample.domain.repositories.NewsSourceRepository
import com.trex.simplesample.ui.base.UiState
import com.trex.simplesample.utils.DispatcherProvider
import com.trex.simplesample.utils.NetworkHelper
import com.trex.simplesample.utils.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourceViewModel @Inject constructor(
    private val newsSourceRepository: NewsSourceRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
    private val logger: Logger
) : ViewModel() {

    private val _newsSourceUiState = MutableStateFlow<UiState<List<NewsSources>>>(UiState.Loading)
    val newsSourceUiState: StateFlow<UiState<List<NewsSources>>> = _newsSourceUiState

    init {
        startFetchingArticles()
    }

    fun startFetchingArticles() {
        if (networkHelper.isNetworkConnected()) {
            fetchArticles()
        } else {
            _newsSourceUiState.value = UiState.Error("Data Not found.")
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch(dispatcherProvider.main) {
            newsSourceRepository.getNewsSource()
                .catch { e ->
                    _newsSourceUiState.value = UiState.Error(e.toString())
                }
                .collect { articles ->
                    _newsSourceUiState.value = UiState.Success(articles)
                    logger.d("TopHeadlineViewModel", "Success")
                }
        }
    }
}