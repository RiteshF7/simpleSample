package com.trex.simplesample.ui.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trex.simplesample.di.DefaultCountryName
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.usecase.GetTopHeadlinesUseCase
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
class TopHeadlineViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
    private val logger: Logger,
    @DefaultCountryName private val countryName: String
) : ViewModel() {

    private val _topHeadlineUiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val topHeadlineUiState: StateFlow<UiState<List<Article>>> = _topHeadlineUiState

    init {
        startFetchingArticles()
    }

    fun startFetchingArticles() {
        if (networkHelper.isNetworkConnected()) {
            fetchArticles()
        } else {
            _topHeadlineUiState.value = UiState.Error("Data Not found.")
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch(dispatcherProvider.main) {
            getTopHeadlinesUseCase(countryName)
                .catch { e ->
                    _topHeadlineUiState.value = UiState.Error(e.toString())
                }
                .collect { articles ->
                    _topHeadlineUiState.value = UiState.Success(articles)
                    logger.d("TopHeadlineViewModel", "Success")
                }
        }
    }
}