package ru.braveowlet.kmmpr.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.braveowlet.kmmpr.core.database.table.dogs.DogEntity
import ru.braveowlet.kmmpr.core.database.table.dogs.DogsDao

@Database(
    entities = [
        DogEntity::class,
    ],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogsDao(): DogsDao
}

private const val DATABASE_VERSION = 1
