package com.trex.simplesample.data.remote.models.topheadlines

import com.google.gson.annotations.SerializedName
import com.trex.simplesample.domain.models.Article

data class ApiArticle(
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String = "",
    @SerializedName("urlToImage") val imageUrl: String? = "",
    @SerializedName("source") val apiSource: ApiSource
)

fun List<ApiArticle>.apiArticleListToDomain(country: String): List<Article> {
    val list = mutableListOf<Article>()
    forEach { apiArticle ->
        list.add(apiArticle.toDomain(country))
    }
    return list
}


fun ApiArticle.toDomain(country:String): Article {
    return Article(
        title = this.title,
        description = this.description,
        url = this.url,
        imageUrl = this.imageUrl,
        sourceName = this.apiSource.name,
        country =country
    )
}

