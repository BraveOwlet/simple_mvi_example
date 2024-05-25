package ru.braveowlet.kmmpr.core.database

import ru.braveowlet.kmmpr.core.database.table.dogs.provideDogsDao

val databaseModule
    get() = platformDatabaseModule(fileName = "database.db").apply {
        provideDogsDao()
    }
