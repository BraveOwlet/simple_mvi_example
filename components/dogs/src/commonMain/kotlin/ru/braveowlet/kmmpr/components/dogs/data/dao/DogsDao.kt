package ru.braveowlet.kmmpr.components.dogs.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.components.dogs.data.entity.DogEntity

@Dao
interface DogsDao{

    @Insert
    suspend fun insert(entity : DogEntity)

    @Query("SELECT * FROM DogEntity")
    suspend fun get(): List<DogEntity>

    @Query("SELECT * FROM DogEntity")
    fun observe() : Flow<List<DogEntity>>
}

