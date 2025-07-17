package com.trex.simplesample.data.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.trex.simplesample.data.local.entity.ArticleEntity
import com.trex.simplesample.data.local.entity.NewsSourcesEntity
import com.trex.simplesample.data.local.entity.RemoteKeyEntity
import com.trex.simplesample.utils.AppConstants
import javax.inject.Inject

class NewsDatabaseService @Inject constructor(private val simpleDatabase: NewsDatabase) :
    DatabaseService {


    override suspend fun cacheArticles(articles: List<ArticleEntity>) {
        simpleDatabase.topHeadlinesDao().insertTopHeadlineArticles(articles)
    }

    override fun fetchPaginatedArticles(): PagingSource<Int, ArticleEntity> {
        return simpleDatabase.topHeadlinesDao().getTopHeadlinesArticles(AppConstants.COUNTRY)
    }

    override suspend fun cacheSources(sources: List<NewsSourcesEntity>) {
        simpleDatabase.sourceDao().insertSourcesNews(sources)
    }

    override suspend fun saveRemoteKeys(keys: List<RemoteKeyEntity>) {
        simpleDatabase.remoteKeyDao().insertAll(keys)
    }

    override suspend fun saveRemoteKey(key: RemoteKeyEntity) {
        simpleDatabase.remoteKeyDao().insertKey(key)
    }


    override suspend fun getRemoteKey(id: String): RemoteKeyEntity? {
        return simpleDatabase.remoteKeyDao().remoteKeyById(id)
    }

    override suspend fun clearAllCaches(remoteKey: String) {
        simpleDatabase.topHeadlinesDao().clearTopHeadlinesArticles(AppConstants.COUNTRY)
        simpleDatabase.sourceDao().clearSourcesNews()
        simpleDatabase.remoteKeyDao().clearRemoteKey(remoteKey)
    }

    suspend fun withTransaction(block: suspend () -> Unit) = simpleDatabase.withTransaction {
        block()
    }
}
