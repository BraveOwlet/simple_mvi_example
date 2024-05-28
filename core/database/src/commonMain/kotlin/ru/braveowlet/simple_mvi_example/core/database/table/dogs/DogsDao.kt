package ru.braveowlet.simple_mvi_example.core.database.table.dogs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: DogEntity)

    @Query("SELECT * FROM DogEntity")
    suspend fun get(): List<DogEntity>

    @Query("SELECT * FROM DogEntity")
    fun observe(): Flow<List<DogEntity>>
}
