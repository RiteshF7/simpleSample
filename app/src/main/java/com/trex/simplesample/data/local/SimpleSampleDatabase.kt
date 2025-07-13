package com.trex.simplesample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trex.simplesample.data.local.daos.SimpleEntityDao
import com.trex.simplesample.data.local.entities.SimpleEntity



@Database(entities = [SimpleEntity::class], version = 1, exportSchema = false)
abstract class SimpleSampleDatabase : RoomDatabase() {
    abstract fun simpleDao(): SimpleEntityDao
}