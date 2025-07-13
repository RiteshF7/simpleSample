package com.trex.simplesample.di.modules

import com.trex.simplesample.data.remote.ApiKeyInterceptor
import com.trex.simplesample.data.remote.SampleNetworkService
import com.trex.simplesample.di.ApiKey
import com.trex.simplesample.di.BaseUrl
import android.content.Context
import androidx.room.Room
import com.trex.simplesample.data.local.SampleDatabase
import com.trex.simplesample.di.DatabaseName
import com.trex.simplesample.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    @DatabaseName
    fun providesDatabaseName(): String {
        return Constants.DATABASE_NAME
    }

    @Provides
    @BaseUrl
    fun providesBaseUrl(): String {
        return Constants.DATABASE_NAME
    }

    @Provides
    @ApiKey
    fun providesApiKey(): String {
        return Constants.DATABASE_NAME
    }

    @Provides
    @Singleton
    fun providesNetworkInterceptor(@ApiKey apiKey: String): ApiKeyInterceptor {
        return ApiKeyInterceptor(apiKey);
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesNetworkClient(networkInterceptor: ApiKeyInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(networkInterceptor)
            .build()

    @Provides
    @Singleton
    fun providesNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): SampleNetworkService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()
            .create(SampleNetworkService::class.java)
    }

    @Provides
    @DatabaseName
    fun providesDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): SampleDatabase {
        return Room
            .databaseBuilder(context, SampleDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()
    }

}