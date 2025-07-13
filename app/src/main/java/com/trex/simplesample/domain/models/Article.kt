package com.trex.simplesample.domain.models

data class Article(
    val title: String = "",
    val description: String?,
    val url: String = "",
    val imageUrl: String = "",
    val sourceName:String,
    val country:String =""
)