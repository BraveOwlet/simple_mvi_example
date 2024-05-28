package ru.braveowlet.simple_mvi_example.core.database

import ru.braveowlet.simple_mvi_example.core.database.table.dogs.DogsDao

val databaseModule
    get() = platformDatabaseModule(fileName = "database.db")
        .apply {
            single<DogsDao> { get<AppDatabase>().dogsDao() }
        }
