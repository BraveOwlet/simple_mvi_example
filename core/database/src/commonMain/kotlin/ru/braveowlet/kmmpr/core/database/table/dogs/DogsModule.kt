package ru.braveowlet.kmmpr.core.database.table.dogs

import org.koin.core.module.Module
import ru.braveowlet.kmmpr.core.database.AppDatabase

internal fun Module.provideDogsDao() = single<DogsDao> { get<AppDatabase>().dogsDao() }
