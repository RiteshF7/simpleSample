package com.trex.simplesample.ui.topheadlines.topHeadlinesPaging

import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.repositories.TopHeadlinesPagingSource
import com.trex.simplesample.di.DefaultCountryName
import javax.inject.Inject

class TopHeadlinesPagingSourceFactory @Inject constructor(
    private val networkService: NewsNetworkService,
    @DefaultCountryName private val country: String
) {
    fun create(): TopHeadlinesPagingSource {
        return TopHeadlinesPagingSource(networkService, country)
    }
}