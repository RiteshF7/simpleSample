package com.trex.simplesample.data.remote.repositories

import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.models.topheadlines.apiArticleListToDomain
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.TopHeadlinesRepository
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
            val articlesList = it.apiArticles
            articlesList?.apiArticleListToDomain(countryId) ?: emptyList()
        }

    }
}