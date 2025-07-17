package com.trex.simplesample.domain.repositories

import com.trex.simplesample.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNewsBySource(sourceId: String): Flow<List<Article>>
    fun getNewsByCountry(countryId: String): Flow<List<Article>>
    fun getNewsByLanguage(languageId: String): Flow<List<Article>>
}