package ru.braveowlet.kmmpr.components.dogs.data.dao

import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.core.database.DogEntity

interface DogsDao{
    fun get(): List<DogEntity>
    fun observe() : Flow<List<DogEntity>>
    suspend fun insert(entity : DogEntity)
}
