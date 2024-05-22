package ru.braveowlet.kmmpr.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.braveowlet.kmmpr.core.database.domain.dogs.DogEntity
import ru.braveowlet.kmmpr.core.database.domain.dogs.DogsDao

@Database(
    entities = [
        DogEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogsDao(): DogsDao
}