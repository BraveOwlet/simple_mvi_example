package ru.braveowlet.kmmpr.core.database.domain.dogs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao{

    @Insert
    suspend fun insert(entity : DogEntity)

    @Query("SELECT * FROM DogEntity")
    suspend fun get(): List<DogEntity>

    @Query("SELECT * FROM DogEntity")
    fun observe() : Flow<List<DogEntity>>
}

