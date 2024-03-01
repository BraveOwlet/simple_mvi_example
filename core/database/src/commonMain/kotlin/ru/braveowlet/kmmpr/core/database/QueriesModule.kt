package ru.braveowlet.kmmpr.core.database

import org.koin.dsl.module

val queriesModule get() = module {
    single<DogsTableQueries> { get<Database>().dogsTableQueries }
    single<SharedTableQueries> { get<Database>().sharedTableQueries }
}
