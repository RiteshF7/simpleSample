package com.trex.simplesample.data.remote

import com.trex.simplesample.di.ApiKey
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject



class ApiKeyInterceptor @Inject constructor(@ApiKey private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithHeader = originalRequest.newBuilder()
            .header("X-Api-Key", apiKey)
            .build()

        // Log request details
        println("Request: ${requestWithHeader.method} ${requestWithHeader.url}")
        requestWithHeader.headers.forEach { header ->
            println("Header: ${header.first} = ${header.second}")
        }

        val response = chain.proceed(requestWithHeader)

        // Log response details
        println("Response: ${response.code} ${response.message}")
        println("URL: ${response.request.url}")
        println("Response Headers:")
        response.headers.forEach { header ->
            println("Header: ${header.first} = ${header.second}")
        }

        return response
    }
}