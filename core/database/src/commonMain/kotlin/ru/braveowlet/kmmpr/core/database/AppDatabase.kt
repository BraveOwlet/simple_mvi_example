package ru.braveowlet.kmmpr.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.braveowlet.kmmpr.components.dogs.data.dao.DogsDao
import ru.braveowlet.kmmpr.components.dogs.data.entity.DogEntity

@Database(
    entities = [
        DogEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogsDao(): DogsDao
}
