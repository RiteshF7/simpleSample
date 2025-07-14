package com.trex.simplesample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.trex.simplesample.data.local.entity.NewsSourcesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SourceDao {

    @Transaction
    @Query("SELECT * FROM Sources")
    fun getSourcesNews(): Flow<List<NewsSourcesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSourcesNews(newsSource: List<NewsSourcesEntity>): List<Long>

    @Query("DELETE FROM Sources")
    fun clearSourcesNews(): Int

    @Transaction
    fun deleteAndInsertAllSourceNews(newsSource: List<NewsSourcesEntity>): List<Long> {
        clearSourcesNews()
        return insertSourcesNews(newsSource)
    }
}