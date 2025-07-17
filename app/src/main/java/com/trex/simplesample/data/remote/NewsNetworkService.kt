package com.trex.simplesample.data.remote

import com.trex.simplesample.data.remote.models.newssources.NewsSourcesDto
import com.trex.simplesample.data.remote.models.topheadlines.TopHeadlinesDto
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsNetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): TopHeadlinesDto

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesDto

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") sources: String): TopHeadlinesDto

    @GET("top-headlines")
    suspend fun getNewsByCountry(@Query("country") country: String): TopHeadlinesDto

    @GET("top-headlines")
    suspend fun getNewsByLanguage(@Query("language") language: String): TopHeadlinesDto

    @GET("everything")
    suspend fun getNewsByQueries(@Query("q") queries: String): TopHeadlinesDto
}