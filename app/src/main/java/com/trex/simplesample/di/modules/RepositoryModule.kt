package com.trex.simplesample.di.modules

import com.trex.simplesample.data.remote.repositories.TopHeadlinesPagingRepositoryImpl
import com.trex.simplesample.data.remote.repositories.CountryListRepositoryImpl
import com.trex.simplesample.data.remote.repositories.LanguageListRepositoryImpl
import com.trex.simplesample.data.remote.repositories.NewsRepositoryImpl
import com.trex.simplesample.data.remote.repositories.NewsSourceRepositoryImpl
import com.trex.simplesample.data.remote.repositories.TopHeadlinesRepositoryImpl
import com.trex.simplesample.domain.repositories.TopHeadlinesPagingRepository
import com.trex.simplesample.domain.repositories.CountryRepository
import com.trex.simplesample.domain.repositories.LanguageRepository
import com.trex.simplesample.domain.repositories.NewsRepository
import com.trex.simplesample.domain.repositories.NewsSourceRepository
import com.trex.simplesample.domain.repositories.TopHeadlinesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTopHeadlinesRepository(
        impl: TopHeadlinesRepositoryImpl
    ): TopHeadlinesRepository


    @Binds
    @Singleton
    abstract fun bindNewsSourceRepository(
        impl: NewsSourceRepositoryImpl
    ): NewsSourceRepository


    @Binds
    @Singleton
    abstract fun bindCountryRepository(
        impl: CountryListRepositoryImpl
    ): CountryRepository

    @Binds
    @Singleton
    abstract fun bindLanguageRepository(
        impl: LanguageListRepositoryImpl
    ): LanguageRepository

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository


}