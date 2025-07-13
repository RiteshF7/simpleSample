package com.trex.simplesample.di.modules

import com.trex.simplesample.di.DatabaseName
import com.trex.simplesample.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    @DatabaseName
    fun providesDatabaseName(): String {
        return AppConstants.DATABASE_NAME
    }

}