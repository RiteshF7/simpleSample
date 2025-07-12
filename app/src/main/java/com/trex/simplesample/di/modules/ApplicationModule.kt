package com.trex.simplesample.di.modules

import com.trex.simplesample.di.DatabaseName
import com.trex.simplesample.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    @DatabaseName
    fun providesDatabaseName(): String {
        return Constants.DATABASE_NAME
    }

}