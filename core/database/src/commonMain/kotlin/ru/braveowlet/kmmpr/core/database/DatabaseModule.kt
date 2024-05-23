package ru.braveowlet.kmmpr.core.database

import ru.braveowlet.kmmpr.components.dogs.data.dao.DogsDao

val databaseModule
    get() = platformDatabaseModule(fileName = "database.db").apply {
        single<DogsDao> { get<AppDatabase>().dogsDao() }
    }
