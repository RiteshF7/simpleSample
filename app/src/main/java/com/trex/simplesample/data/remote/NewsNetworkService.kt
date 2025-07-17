package com.trex.simplesample.data.remote

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

}