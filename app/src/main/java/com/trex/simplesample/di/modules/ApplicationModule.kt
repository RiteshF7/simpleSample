package com.trex.simplesample.di.modules

import android.content.Context
import androidx.room.Room
import com.trex.simplesample.data.local.NewsDatabase
import com.trex.simplesample.di.DatabaseName
import com.trex.simplesample.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @DatabaseName
    fun providesDatabaseName(): String {
        return AppConstants.DATABASE_NAME
    }


    @Provides
    @DatabaseName
    fun providesDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): NewsDatabase {
        return Room
            .databaseBuilder(context, NewsDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()
    }

}