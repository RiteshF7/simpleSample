package com.trex.simplesample.data.remote

import com.trex.simplesample.data.remote.models.SampleDto
import retrofit2.http.GET
import retrofit2.http.Query


interface SampleNetworkService {
    @GET("/Sample")
    suspend fun getSample(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): SampleDto

}