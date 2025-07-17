package com.trex.simplesample.data.remote.models.topheadlines

import com.google.gson.annotations.SerializedName
import com.nitinlondhe.newsapp.data.local.entity.SourceEntity
import com.trex.simplesample.domain.models.Source

data class ApiSource(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String = "",
)

fun ApiSource.toSource(): Source {
    return Source(
        id = this.id,
        name = this.name
    )
}

fun ApiSource.toEntity(): SourceEntity {
    return SourceEntity(
        id = this.id,
        name = this.name
    )
}

