package com.trex.simplesample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trex.simplesample.data.local.dao.RemoteKeyDao
import com.trex.simplesample.data.local.dao.SourceDao
import com.trex.simplesample.data.local.dao.TopHeadlinesDao
import com.trex.simplesample.data.local.entity.ArticleEntity
import com.trex.simplesample.data.local.entity.NewsSourcesEntity
import com.trex.simplesample.data.local.entity.RemoteKeyEntity


@Database(
    entities = [ArticleEntity::class, NewsSourcesEntity::class, RemoteKeyEntity::class],
    version = 2,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun sourceDao(): SourceDao
    abstract fun topHeadlinesDao(): TopHeadlinesDao

}