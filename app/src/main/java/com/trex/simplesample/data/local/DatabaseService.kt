package com.trex.simplesample.data.local

import androidx.paging.PagingSource
import com.trex.simplesample.data.local.entity.ArticleEntity
import com.trex.simplesample.data.local.entity.NewsSourcesEntity
import com.trex.simplesample.data.local.entity.RemoteKeyEntity


interface DatabaseService {

    suspend fun cacheArticles(articles: List<ArticleEntity>)

    fun fetchPaginatedArticles(): PagingSource<Int, ArticleEntity>

    suspend fun cacheSources(sources: List<NewsSourcesEntity>)

    suspend fun saveRemoteKeys(keys: List<RemoteKeyEntity>)

    suspend fun saveRemoteKey(key: RemoteKeyEntity)


    suspend fun getRemoteKey(id: String): RemoteKeyEntity?

    suspend fun clearAllCaches(remoteKey: String)



}