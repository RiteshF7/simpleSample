package com.trex.simplesample.data.remote.models.newssources

import com.google.gson.annotations.SerializedName
import com.trex.simplesample.domain.models.NewsSources

data class NewsSourcesDto(
    @SerializedName("status") val status: String = "",
    @SerializedName("sources") val newsSource: List<APINewsSource> = arrayListOf(),
)

fun NewsSourcesDto.toDomainList(): List<NewsSources> = this.newsSource.map { it.toDomain() }


