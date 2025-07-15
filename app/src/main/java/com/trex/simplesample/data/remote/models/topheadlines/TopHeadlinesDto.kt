package com.trex.simplesample.data.remote.models.topheadlines

import com.google.gson.annotations.SerializedName

data class TopHeadlinesDto(
    @SerializedName("status") val status: String = "",
    @SerializedName("totalResults") val totalResults: Int = 0,
    @SerializedName("articles") val apiArticles: List<ApiArticle>? = ArrayList(),
)

