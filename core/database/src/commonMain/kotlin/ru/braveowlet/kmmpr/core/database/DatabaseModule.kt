package ru.braveowlet.kmmpr.core.database

import ru.braveowlet.kmmpr.core.database.domain.dogs.DogsDao

val databaseModule get() =  platformDatabaseModule(
    fileName = "database.db"
).apply {
    single<DogsDao> {
        get<AppDatabase>().dogsDao()
    }
}
