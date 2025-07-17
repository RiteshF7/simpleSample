package com.trex.simplesample.data.remote.repositories

import com.trex.simplesample.domain.models.Language
import com.trex.simplesample.domain.repositories.LanguageRepository
import com.trex.simplesample.utils.AppConstants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class LanguageListRepositoryImpl @Inject constructor() : LanguageRepository {

    override fun getLanguages(): Flow<List<Language>> {
        return flow { emit(AppConstants.LANGUAGES) }
    }

}
