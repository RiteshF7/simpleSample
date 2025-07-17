package com.trex.simplesample.ui.topheadlines.topHeadlinesPaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.TopHeadlinesPagingRepository
import com.trex.simplesample.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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

    val topHeadlineUiState: Flow<PagingData<Article>> =
        paginationTopHeadlineRepository.getTopHeadlines()
            .flowOn(dispatcherProvider.io)
            .cachedIn(viewModelScope)

    init {
        println("📱 ViewModel initialized")
    }
}