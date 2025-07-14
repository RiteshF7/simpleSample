package com.trex.simplesample.data.remote.repositories.topheadlinesoffline

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.trex.simplesample.data.local.NewsDatabaseService
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.TopHeadlinesOfflineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TopHeadlinesOfflineRepositoryImpl @Inject constructor(
    private val databaseService: NewsDatabaseService
) : TopHeadlinesOfflineRepository {
    override fun getTopHeadlinesOfflinePaging(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { databaseService.fetchPaginatedArticles() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    private fun com.trex.simplesample.data.local.entity.ArticleEntity.toDomain(): Article =
        Article(
            title = title,
            description = description,
            url = url,
            imageUrl = imageUrl ?: "",
            sourceName = source.name,
            country = country
        )
}