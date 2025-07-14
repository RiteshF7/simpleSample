package com.trex.simplesample.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.trex.simplesample.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopHeadlinesDao {

    @Transaction
    @Query("SELECT * FROM TopHeadlinesArticle WHERE country =:country")
    fun getTopHeadlinesArticles(country: String): PagingSource<Int, ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopHeadlineArticles(articles: List<ArticleEntity>): List<Long>

    @Query("DELETE FROM TopHeadlinesArticle WHERE country = :country ")
    fun clearTopHeadlinesArticles(country: String)

    @Transaction
    fun deleteAndInsertAllTopHeadlinesArticles(
        articles: List<ArticleEntity>, country: String
    ): List<Long> {
        clearTopHeadlinesArticles(country)
        return insertTopHeadlineArticles(articles)
    }

    @Query("SELECT * FROM TopHeadlinesArticle WHERE sourceId =:sourceId")
    fun getSourceArticle(sourceId: String): Flow<List<ArticleEntity>>

    @Query("DELETE FROM TopHeadlinesArticle WHERE sourceId = :sourceId ")
    fun clearSourceArticles(sourceId: String)

    @Transaction
    fun deleteAllAndInsertAllSourceArticles(
        articles: List<ArticleEntity>,
        sourceID: String
    ): List<Long> {
        clearSourceArticles(sourceID)
        return insertTopHeadlineArticles(articles)
    }

    @Query("SELECT * FROM TopHeadlinesArticle WHERE language =:languageId")
    fun getLanguageArticles(languageId: String): Flow<List<ArticleEntity>>

    @Query("DELETE FROM TopHeadlinesArticle WHERE language = :languageId ")
    fun clearLanguageArticles(languageId: String)

    @Transaction
    fun deleteAllAndInsertAllLanguageArticles(
        articles: List<ArticleEntity>,
        languageId: String
    ): List<Long> {
        clearLanguageArticles(languageId)
        return insertTopHeadlineArticles(articles)
    }

}