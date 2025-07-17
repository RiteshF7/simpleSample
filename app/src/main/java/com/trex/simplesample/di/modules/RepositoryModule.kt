package com.trex.simplesample.di.modules

import com.trex.simplesample.data.remote.repositories.CountryListRepositoryImpl
import com.trex.simplesample.data.remote.repositories.LanguageListRepositoryImpl
import com.trex.simplesample.data.remote.repositories.NewsRepositoryImpl
import com.trex.simplesample.data.remote.repositories.NewsSourceRepositoryImpl
import com.trex.simplesample.data.remote.repositories.SearchRepositoryImpl
import com.trex.simplesample.data.remote.repositories.TopHeadlinesPagingRepositoryImpl
import com.trex.simplesample.data.remote.repositories.TopHeadlinesRepositoryImpl
import com.trex.simplesample.domain.repositories.CountryRepository
import com.trex.simplesample.domain.repositories.LanguageRepository
import com.trex.simplesample.domain.repositories.NewsRepository
import com.trex.simplesample.domain.repositories.NewsSourceRepository
import com.trex.simplesample.domain.repositories.SearchRepository
import com.trex.simplesample.domain.repositories.TopHeadlinesPagingRepository
import com.trex.simplesample.domain.repositories.TopHeadlinesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTopHeadlinesRepository(
        impl: TopHeadlinesRepositoryImpl
    ): TopHeadlinesRepository


    @Binds
    @ViewModelScoped
    abstract fun bindNewsSourceRepository(
        impl: NewsSourceRepositoryImpl
    ): NewsSourceRepository


    @Binds
    @ViewModelScoped
    abstract fun bindCountryRepository(
        impl: CountryListRepositoryImpl
    ): CountryRepository

    @Binds
    @ViewModelScoped
    abstract fun bindLanguageRepository(
        impl: LanguageListRepositoryImpl
    ): LanguageRepository

    @Binds
    @ViewModelScoped
    abstract fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository

    @Binds
    @ViewModelScoped
    abstract fun bindSearchRepository(
        impl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    @ViewModelScoped
    abstract fun topHeadLinesRepository(
        impl: TopHeadlinesPagingRepositoryImpl
    ): TopHeadlinesPagingRepository


}