package ru.braveowlet.kmmpr.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun platformDatabaseModule(
    fileName: String,
): Module = module(createdAtStart = true) {
    single<AppDatabase> {
        getDatabase(get(), fileName)
    }
}

private fun getDatabaseBuilder(
    context: Context,
    fileName: String,
): RoomDatabase.Builder<AppDatabase> = Room
    .databaseBuilder<AppDatabase>(
        context = context.applicationContext,
        name = context.applicationContext.getDatabasePath(fileName).absolutePath
    )
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)

private fun getDatabase(
    context: Context,
    fileName: String,
): AppDatabase {
    return getDatabaseBuilder(
        context = context,
        fileName = fileName
    ).build()
}
