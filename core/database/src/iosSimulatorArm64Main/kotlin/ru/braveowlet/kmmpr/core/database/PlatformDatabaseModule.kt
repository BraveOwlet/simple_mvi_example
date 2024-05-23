package ru.braveowlet.kmmpr.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

internal actual fun platformDatabaseModule(
    fileName: String,
): Module = module(createdAtStart = true) {
    single<AppDatabase> {
        getDatabase(fileName)
    }
}

fun getDatabaseBuilder(
    fileName: String,
): RoomDatabase.Builder<AppDatabase> = Room
    .databaseBuilder<AppDatabase>(
        name = NSHomeDirectory() + "/$fileName",
        factory = { AppDatabase::class.instantiateImpl() }
    )
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)


fun getDatabase(
    fileName: String,
): AppDatabase = getDatabaseBuilder(fileName).build()

