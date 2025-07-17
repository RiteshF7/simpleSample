package com.trex.simplesample.domain.repositories

import com.trex.simplesample.domain.models.NewsSources
import kotlinx.coroutines.flow.Flow

fun interface NewsSourceRepository {
    fun getNewsSource(): Flow<List<NewsSources>>
}