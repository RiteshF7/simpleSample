package com.trex.simplesample.di.modules

import android.content.Context
import com.trex.simplesample.data.remote.ApiKeyInterceptor
import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.repositories.TopHeadlinesPagingSource
import com.trex.simplesample.di.ApiKey
import com.trex.simplesample.di.BaseUrl
import com.trex.simplesample.di.DefaultCountryName
import com.trex.simplesample.utils.AppConstants
import com.trex.simplesample.utils.DefaultDispatcherProvider
import com.trex.simplesample.utils.DispatcherProvider
import com.trex.simplesample.utils.NetworkHelper
import com.trex.simplesample.utils.NetworkHelperImpl
import com.trex.simplesample.utils.logger.AppLogger
import com.trex.simplesample.utils.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @BaseUrl
    fun providesBaseUrl(): String {
        return AppConstants.BASE_URL
    }


    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @Provides
    @ApiKey
    fun providesApiKey(): String {
        return AppConstants.API_KEY
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
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context)
    }

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
    ): NewsNetworkService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()
            .create(NewsNetworkService::class.java)
    }

    @Provides
    @DefaultCountryName
    fun providesDefaultCountryName(): String {
        return AppConstants.COUNTRY
    }


    @Provides
    @Singleton
    fun providesTopHeadlinePagingSource(
        networkService: NewsNetworkService,
        @DefaultCountryName countryName: String
    ): TopHeadlinesPagingSource {
        return TopHeadlinesPagingSource(networkService, countryName)
    }
}