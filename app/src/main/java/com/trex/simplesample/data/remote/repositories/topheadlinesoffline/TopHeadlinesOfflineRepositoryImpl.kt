package com.trex.simplesample.data.remote.repositories.topheadlinesoffline

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.trex.simplesample.data.local.NewsDatabaseService
import com.trex.simplesample.data.local.entity.toDomain
import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.TopHeadlinesOfflineRepository
import com.trex.simplesample.utils.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TopHeadlinesOfflineRepositoryImpl @Inject constructor(
    private val networkService: NewsNetworkService,
    private val databaseService: NewsDatabaseService
) : TopHeadlinesOfflineRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getTopHeadlinesOfflinePaging(): Flow<PagingData<Article>> {
        return Pager(
            remoteMediator = TopHeadlinesRemoteMediator(networkService, databaseService),
            config = PagingConfig(
                prefetchDistance = 2,
                pageSize = AppConstants.PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                val data = databaseService.fetchPaginatedArticles()
                data
            }
        ).flow.map { pagedData ->
            pagedData.map { entity ->
                entity.toDomain()
            }

        }


    }
}