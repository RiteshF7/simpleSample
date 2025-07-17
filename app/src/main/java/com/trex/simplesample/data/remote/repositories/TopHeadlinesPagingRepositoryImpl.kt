package com.trex.simplesample.data.remote.repositories

import android.nfc.tech.MifareUltralight.PAGE_SIZE
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

    override fun getTopHeadlines(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                prefetchDistance = 2,
                pageSize = AppConstants.PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { topHeadlinesPagingSourceFactory.create() }
        ).flow
    }
}