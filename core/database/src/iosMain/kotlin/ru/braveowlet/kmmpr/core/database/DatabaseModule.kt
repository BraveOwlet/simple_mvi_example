package ru.braveowlet.kmmpr.core.database

import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseModule: Module = module {
    single<Database> { Database(DatabaseDriverFactory().createDriver()) }
}
