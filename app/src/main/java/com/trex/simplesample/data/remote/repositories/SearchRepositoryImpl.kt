package com.trex.simplesample.data.remote.repositories

import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.models.topheadlines.toDomain
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.SearchRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class SearchRepositoryImpl @Inject constructor(private val networkService: NewsNetworkService) :
    SearchRepository {

    override fun getNewsByQueries(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByQueries(query))
        }.map {
            it.apiArticles?.map { apiArticle ->
                apiArticle.toDomain(query)
            } ?: emptyList()
        }
    }
}
