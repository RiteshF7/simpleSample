package com.trex.simplesample.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String, // Use meaningful key: e.g., itemId or query-based key
    val prevKey: Int?,
    val nextKey: Int?
)