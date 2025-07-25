package com.trex.simplesample.domain.usecase


import androidx.paging.PagingData
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.domain.repositories.TopHeadlinesPagingRepository
import com.trex.simplesample.domain.repositories.TopHeadlinesRepository
import com.trex.simplesample.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val topHeadlineRepository: TopHeadlinesRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    operator fun invoke(country: String): Flow<List<Article>> =
        topHeadlineRepository.getTopHeadlinesArticles(country)
            .flowOn(dispatcherProvider.io)
}

class GetTopHeadlinesPagingUseCase @Inject constructor(
    private val topHeadlineRepository: TopHeadlinesPagingRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    operator fun invoke(): Flow<PagingData<Article>> =
        topHeadlineRepository.getTopHeadlines()
            .flowOn(dispatcherProvider.io)
}