package com.trex.simplesample.data.remote.repositories

import com.trex.simplesample.domain.models.CountryDto
import com.trex.simplesample.domain.repositories.CountryRepository
import com.trex.simplesample.utils.AppConstants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class CountryListRepositoryImpl @Inject constructor() : CountryRepository {

    override fun getCountry(): Flow<List<CountryDto>> {
        return flow { emit(AppConstants.COUNTRIES) }
    }
}
