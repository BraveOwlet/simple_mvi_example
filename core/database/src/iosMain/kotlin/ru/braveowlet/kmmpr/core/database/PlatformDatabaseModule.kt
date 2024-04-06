package ru.braveowlet.kmmpr.core.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun platformDatabaseModule(
    fileName: String,
): Module = module {
    single<Database> {
        Database(
            NativeSqliteDriver(
                Database.Schema.synchronous(),
                fileName,
            )
        )
    }
}
