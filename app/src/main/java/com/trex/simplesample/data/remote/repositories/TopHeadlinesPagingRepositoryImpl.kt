package com.trex.simplesample.data.remote.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.TopHeadlinesPagingRepository
import com.trex.simplesample.ui.topheadlines.topHeadlinesPaging.TopHeadlinesPagingSourceFactory
import com.trex.simplesample.utils.AppConstants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TopHeadlinesPagingRepositoryImpl @Inject constructor(
    private val topHeadlinesPagingSourceFactory: TopHeadlinesPagingSourceFactory
) : TopHeadlinesPagingRepository {

    private val pagerFlow: Flow<PagingData<Article>> by lazy {
        Pager(
            config = PagingConfig(
                pageSize = AppConstants.PAGE_SIZE,
                initialLoadSize = AppConstants.PAGE_SIZE,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { topHeadlinesPagingSourceFactory.create() }
        ).flow
    }

    override fun getTopHeadlines(): Flow<PagingData<Article>> = pagerFlow
}