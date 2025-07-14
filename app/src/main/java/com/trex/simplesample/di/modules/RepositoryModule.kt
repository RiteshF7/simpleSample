package com.trex.simplesample.di.modules

import com.trex.simplesample.data.local.DatabaseService
import com.trex.simplesample.data.local.NewsDatabase
import com.trex.simplesample.data.local.NewsDatabaseService
import com.trex.simplesample.data.remote.repositories.TopHeadlinesRepositoryImpl
import com.trex.simplesample.data.remote.repositories.topheadlinesoffline.TopHeadlinesOfflineRepositoryImpl
import com.trex.simplesample.domain.repositories.TopHeadlinesOfflineRepository
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
    abstract fun bindTopHeadlinesOfflineRepository(
        impl: TopHeadlinesOfflineRepositoryImpl
    ): TopHeadlinesOfflineRepository

    @Binds
    @Singleton
    abstract fun providesNewsDatabaseService(
        impl: NewsDatabaseService
    ): DatabaseService

}