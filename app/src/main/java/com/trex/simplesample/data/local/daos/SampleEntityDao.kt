package com.trex.simplesample.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.trex.simplesample.data.local.entities.SampleEntity

@Dao
interface SampleEntityDao {

//    @Query("SELECT * FROM sample_entity ORDER BY id ASC")
//    fun getPagedEntities(): PagingSource<Int, SimpleEntity>

    @Query("SELECT * FROM sample_entity WHERE id = :id")
    suspend fun getById(id: Int): SampleEntity?

    @Query("DELETE FROM sample_entity")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM sample_entity")
    suspend fun count(): Int
}