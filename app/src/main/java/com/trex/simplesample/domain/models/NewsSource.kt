package com.trex.simplesample.domain.models

data class NewsSources(
    val sourceId: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)
