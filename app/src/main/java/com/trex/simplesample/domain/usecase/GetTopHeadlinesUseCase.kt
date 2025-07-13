package com.trex.simplesample.domain.usecase


import com.trex.simplesample.data.remote.models.topheadlines.apiArticleListToDomain
import com.trex.simplesample.data.remote.repositories.TopHeadlineRepository
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    operator fun invoke(country: String): Flow<List<Article>> =
        topHeadlineRepository.getTopHeadlinesArticles(country)
            .map { articles ->
                articles.apiArticleListToDomain(country)
            }
            .flowOn(dispatcherProvider.io)
}