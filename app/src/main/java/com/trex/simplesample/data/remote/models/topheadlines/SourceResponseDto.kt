package com.trex.simplesample.data.remote.models.topheadlines

import com.google.gson.annotations.SerializedName
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