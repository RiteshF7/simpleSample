package com.trex.simplesample.domain.repositories

import androidx.paging.PagingData
import com.trex.simplesample.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface TopHeadlinesPagingRepository {
    fun getTopHeadlines(): Flow<PagingData<Article>>
}