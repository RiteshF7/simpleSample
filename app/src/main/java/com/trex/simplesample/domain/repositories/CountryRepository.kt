package com.trex.simplesample.domain.repositories

import com.trex.simplesample.domain.models.CountryDto
import kotlinx.coroutines.flow.Flow

fun interface CountryRepository {
    fun getCountry(): Flow<List<CountryDto>>
}