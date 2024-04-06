package ru.braveowlet.kmmpr.core.database

val databaseModule = platformDatabaseModule(
    fileName = "database.db"
).apply {
    single<Queries> {
        Queries(
            get<Database>().sharedTableQueries,
            get<Database>().dogsTableQueries
        )
    }
}
