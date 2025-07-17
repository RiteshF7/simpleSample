package com.trex.simplesample.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nitinlondhe.newsapp.data.local.entity.SourceEntity
import com.trex.simplesample.domain.models.Article

@Entity(tableName = "TopHeadlinesArticle")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "article_id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") val url: String = "",
    @ColumnInfo(name = "urlToImage") val imageUrl: String? = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "language") val language: String = "",
    @Embedded val source: SourceEntity
)

fun ArticleEntity.toDomain() = Article(
    title = title,
    description = description,
    url = url,
    imageUrl = imageUrl ?: "",
    country = country,
    sourceName = source.name
)