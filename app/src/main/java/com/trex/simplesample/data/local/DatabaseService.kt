package com.trex.simplesample.data.local

import com.trex.simplesample.data.local.entities.SampleEntity

interface DatabaseService {
    suspend fun getSample(): SampleEntity
}