package ru.braveowlet.kmmpr.core.database.di

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.braveowlet.kmmpr.core.database.Database

internal actual fun platformDatabaseModule(
    fileName: String,
): Module = module {
    single<Database> {
        Database(
            AndroidSqliteDriver(
                Database.Schema.synchronous(),
                androidContext(),
                fileName,
            )
        )
    }
}
