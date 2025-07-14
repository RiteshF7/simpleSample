package com.trex.simplesample.ui.topheadlines.topHeadlinesPaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.TopHeadlinesPagingRepository
import com.trex.simplesample.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopHeadlinesPagingViewmodel @Inject constructor(
    private val paginationTopHeadlineRepository: TopHeadlinesPagingRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _topHeadlineUiState =
        MutableStateFlow<PagingData<Article>>(value = PagingData.empty())

    val topHeadlineUiState: StateFlow<PagingData<Article>> = _topHeadlineUiState

    init {
        startFetchingArticles()
    }

    private fun startFetchingArticles() {
        viewModelScope.launch(dispatcherProvider.main) {
            paginationTopHeadlineRepository.getTopHeadlines()
                .flowOn(dispatcherProvider.io)
                .catch { e -> }
                .cachedIn(viewModelScope)
                .collect {
                    _topHeadlineUiState.value = it
                }

        }
    }

}