package com.trex.simplesample.data.remote.repositories

import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.models.topheadlines.apiArticleListToDomain
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.TopHeadlinesRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor(private val networkService: NewsNetworkService) :
    TopHeadlinesRepository {

    override fun getTopHeadlinesArticles(countryId: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(countryId))
        }.map {
            it.apiArticles.apiArticleListToDomain(countryId)
        }

    }
}