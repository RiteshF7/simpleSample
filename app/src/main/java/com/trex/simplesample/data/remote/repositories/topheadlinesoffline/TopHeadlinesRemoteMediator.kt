package com.trex.simplesample.data.remote.repositories.topheadlinesoffline

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.trex.simplesample.data.local.NewsDatabaseService
import com.trex.simplesample.data.local.entity.ArticleEntity
import com.trex.simplesample.data.local.entity.RemoteKeyEntity
import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.models.topheadlines.toEntity
import com.trex.simplesample.utils.AppConstants
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TopHeadlinesRemoteMediator @Inject constructor(
    private val networkService: NewsNetworkService,
    private val databaseService: NewsDatabaseService
) : RemoteMediator<Int, ArticleEntity>() {

    private val feedKey = "top_headlines_us"

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        val pageSize = state.config.pageSize
        val country = AppConstants.COUNTRY

        val page = when (loadType) {
            LoadType.REFRESH -> {
                databaseService.getRemoteKey(feedKey)?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKey = databaseService.getRemoteKey(feedKey)
                val prevKey = remoteKey?.prevKey
                if (prevKey == null || prevKey <= 0) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKey = databaseService.getRemoteKey(feedKey)
                val nextKey = remoteKey?.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                nextKey
            }
        }

        return try {
            val response = networkService.getTopHeadlines(country, page, pageSize)
            val articlesList = response.apiArticles
            val articleEntities = articlesList?.map { it.toEntity(country) } ?: emptyList()
            val endOfPaginationReached = articleEntities.isEmpty()

            val newKey = RemoteKeyEntity(
                id = feedKey,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (endOfPaginationReached) null else page + 1
            )

            return try {
                databaseService.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        databaseService.clearAllCaches(feedKey)
                    }
                    databaseService.cacheArticles(articleEntities)
                    databaseService.saveRemoteKey(newKey)
                }

                MediatorResult.Success(endOfPaginationReached)
            } catch (e: Exception) {
                Log.e("RemoteMediator", "Transaction failed: ${e.localizedMessage}", e)
                MediatorResult.Error(e)
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}