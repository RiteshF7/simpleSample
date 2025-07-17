package com.trex.simplesample.data.remote.repositories

import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.models.newssources.toDomainList
import com.trex.simplesample.domain.models.NewsSources
import com.trex.simplesample.domain.repositories.NewsSourceRepository
import com.trex.simplesample.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsSourceRepositoryImpl @Inject constructor(
    private val networkService: NewsNetworkService,
    private val dispatcherProvider: DispatcherProvider,
) :
    NewsSourceRepository {
    override fun getNewsSource(): Flow<List<NewsSources>> = flow {
        emit(networkService.getNewsSources())
    }.map { newsResponse ->
        newsResponse.toDomainList()
    }
        .flowOn(dispatcherProvider.io)


}