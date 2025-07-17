package com.trex.simplesample.domain.repositories

import com.trex.simplesample.domain.models.Language
import kotlinx.coroutines.flow.Flow

fun interface LanguageRepository {
    fun getLanguages(): Flow<List<Language>>
}