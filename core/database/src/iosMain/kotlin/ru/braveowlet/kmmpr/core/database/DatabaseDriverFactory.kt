package ru.braveowlet.kmmpr.core.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class DatabaseDriverFactory {
    fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Database.Schema.synchronous(), "database.db")
    }
}
