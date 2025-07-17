package com.trex.simplesample.domain.repositories

import com.trex.simplesample.domain.models.Article
import kotlinx.coroutines.flow.Flow

fun interface SearchRepository {
    fun getNewsByQueries(query: String): Flow<List<Article>>
}