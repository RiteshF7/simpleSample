package com.trex.simplesample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trex.simplesample.data.local.daos.SampleEntityDao
import com.trex.simplesample.data.local.entities.SampleEntity



@Database(entities = [SampleEntity::class], version = 1, exportSchema = false)
abstract class SampleDatabase : RoomDatabase() {
    abstract  fun simpleDao(): SampleEntityDao
}