package com.trex.simplesample.data.remote.models.topheadlines
import com.google.gson.annotations.SerializedName

data class ApiArticle(
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String = "",
    @SerializedName("urlToImage") val imageUrl: String = "",
    @SerializedName("source") val apiSource: ApiSource
)


