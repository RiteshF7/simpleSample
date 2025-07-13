package com.trex.simplesample.di.modules

import android.content.Context
import com.trex.simplesample.data.remote.ApiKeyInterceptor
import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.di.ApiKey
import com.trex.simplesample.di.BaseUrl
import com.trex.simplesample.di.DatabaseName
import com.trex.simplesample.utils.AppConstants
import com.trex.simplesample.utils.DefaultDispatcherProvider
import com.trex.simplesample.utils.DispatcherProvider
import com.trex.simplesample.utils.NetworkHelper
import com.trex.simplesample.utils.NetworkHelperImpl
import com.trex.simplesample.utils.logger.AppLogger
import com.trex.simplesample.utils.logger.Logger
import dagger.Binds
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
class ApplicationModule {

    @Provides
    @DatabaseName
    fun providesDatabaseName(): String {
        return AppConstants.DATABASE_NAME
    }


}