package com.trex.simplesample.data.local

import com.trex.simplesample.data.local.entities.SampleEntity
import javax.inject.Inject

class SimpleDatabaseService @Inject constructor(private val simpleDatabase: SampleDatabase):
    DatabaseService {
    override suspend fun getSample(): SampleEntity {
        return simpleDatabase.simpleDao().getById(1)?: SampleEntity(1,"Sample")
    }

}