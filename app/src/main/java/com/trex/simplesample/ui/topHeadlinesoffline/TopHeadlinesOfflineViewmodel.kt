package com.trex.simplesample.ui.topheadlines.topHeadlinesoffline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.trex.simplesample.data.local.entity.ArticleEntity
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.TopHeadlinesOfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesOfflineViewmodel @Inject constructor(
    repository: TopHeadlinesOfflineRepository
) : ViewModel() {
    val topHeadlinesFlow: Flow<PagingData<Article>> =
        repository
            .getTopHeadlinesOfflinePaging()
            .cachedIn(viewModelScope)
}