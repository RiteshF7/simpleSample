package com.trex.simplesample.data.remote.repositories

import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.models.topheadlines.ApiArticle
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class TopHeadlineRepository @Inject constructor(private val networkService: NewsNetworkService) {

    fun getTopHeadlinesArticles(countryID: String): Flow<List<ApiArticle>> {
        return flow { emit(networkService.getTopHeadlines(countryID)) }
            .map {
                it.apiArticles
            }
    }
}