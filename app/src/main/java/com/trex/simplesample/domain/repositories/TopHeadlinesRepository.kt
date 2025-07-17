package com.trex.simplesample.domain.repositories

import com.trex.simplesample.domain.models.Article
import kotlinx.coroutines.flow.Flow

fun interface TopHeadlinesRepository {
    fun getTopHeadlinesArticles(countryId: String): Flow<List<Article>>
}