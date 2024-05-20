package ru.braveowlet.kmmpr.core.database.data.dogs

import org.koin.core.module.Module
import ru.braveowlet.kmmpr.core.database.Database
import ru.braveowlet.kmmpr.core.database.domain.dogs.DogsDao

internal fun Module.addSingleDogsDao() = single<DogsDao> {
    DogsDaoImpl(
        get<Database>().dogTableQueries
    )
}
