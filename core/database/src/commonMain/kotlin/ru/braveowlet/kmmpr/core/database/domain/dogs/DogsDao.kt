package ru.braveowlet.kmmpr.core.database.domain.dogs

import kotlinx.coroutines.flow.Flow

interface DogsDao{
    fun get(): List<DogEntity>
    fun observe() : Flow<List<DogEntity>>
    suspend fun insert(entity : DogEntity)
}
