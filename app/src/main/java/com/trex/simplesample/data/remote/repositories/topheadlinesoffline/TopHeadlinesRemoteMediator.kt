package com.trex.simplesample.data.remote.repositories.topheadlinesoffline

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.trex.simplesample.data.local.NewsDatabaseService
import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.domain.models.Article
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TopHeadlinesRemoteMediator @Inject constructor(
    private val networkService: NewsNetworkService,
    private val databaseService: NewsDatabaseService
) : RemoteMediator<Int, Article>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        val pageSize = state.config.pageSize
        val country = "us"
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = state.anchorPosition?.let { anchorPosition ->
                    state.closestItemToPosition(anchorPosition)?.url?.let { url ->
                        databaseService.getRemoteKey(url)?.nextKey?.minus(1)
                    }
                }
                remoteKeys ?: 1
            }

            LoadType.PREPEND -> {
                val firstItem = state.firstItemOrNull() ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                val remoteKey = databaseService.getRemoteKey(firstItem.url)
                remoteKey?.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                val remoteKey = databaseService.getRemoteKey(lastItem.url)
                remoteKey?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }
        try {
            val response =
                networkService.getTopHeadlines(country = country, page = page, pageSize = pageSize)
            val apiArticles = response.apiArticles
            val endOfPaginationReached = apiArticles.isEmpty()
            // Map ApiArticle to ArticleEntity
            val articleEntities = apiArticles.map { apiArticle ->
                com.trex.simplesample.data.local.entity.ArticleEntity(
                    title = apiArticle.title,
                    description = apiArticle.description,
                    url = apiArticle.url,
                    imageUrl = apiArticle.imageUrl,
                    country = country,
                    language = "",
                    source = com.nitinlondhe.newsapp.data.local.entity.SourceEntity(
                        id = apiArticle.apiSource.id,
                        name = apiArticle.apiSource.name
                    )
                )
            }
            // RemoteKeys for each
            val keys = articleEntities.map {
                com.trex.simplesample.data.local.entity.RemoteKeyEntity(
                    id = it.url,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (endOfPaginationReached) null else page + 1
                )
            }

            // Room transaction, using databaseService
            if (loadType == LoadType.REFRESH) {
                databaseService.clearAllCaches()
            }
            if (articleEntities.isNotEmpty()) {
                databaseService.cacheArticles(articleEntities)
                databaseService.saveRemoteKeys(keys)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}