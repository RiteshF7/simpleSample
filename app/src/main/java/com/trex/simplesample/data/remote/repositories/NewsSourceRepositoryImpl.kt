package com.trex.simplesample.data.remote.repositories

import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.models.newssources.toDomainList
import com.trex.simplesample.domain.models.NewsSources
import com.trex.simplesample.domain.repositories.NewsSourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsSourceRepositoryImpl @Inject constructor(private val networkService: NewsNetworkService) :
    NewsSourceRepository {
    override fun getNewsSource(): Flow<List<NewsSources>> = flow {
        emit(networkService.getNewsSources())
    }.map { newsResponse ->
        newsResponse.toDomainList()
    }

}