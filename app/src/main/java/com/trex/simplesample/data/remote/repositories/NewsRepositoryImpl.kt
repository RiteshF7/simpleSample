package com.trex.simplesample.data.remote.repositories


import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.models.topheadlines.toDomain
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.NewsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class NewsRepositoryImpl @Inject constructor(
    private val networkService: NewsNetworkService,
) : NewsRepository {

    override fun getNewsBySource(sourceId: String): Flow<List<Article>> {
        return flow { emit(networkService.getNewsBySources(sourceId)) }
            .map {
                it.apiArticles?.map { apiArticle -> apiArticle.toDomain() } ?: emptyList()
            }
    }


    override fun getNewsByCountry(countryId: String): Flow<List<Article>> {
        return flow { emit(networkService.getNewsByCountry(countryId)) }
            .map {
                it.apiArticles?.map { apiArticle -> apiArticle.toDomain(countryId) } ?: emptyList()
            }
    }


    override fun getNewsByLanguage(languageId: String): Flow<List<Article>> {
        return flow { emit(networkService.getNewsByLanguage(languageId)) }
            .map {
                it.apiArticles?.map { apiArticle -> apiArticle.toDomain() } ?: emptyList()
            }
    }

}