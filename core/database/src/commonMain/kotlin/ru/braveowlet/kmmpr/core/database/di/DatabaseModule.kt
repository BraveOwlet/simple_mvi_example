package ru.braveowlet.kmmpr.core.database.di

import ru.braveowlet.kmmpr.core.database.data.dogs.addSingleDogsDao

val databaseModule get() =  platformDatabaseModule(
    fileName = "database.db"
).apply {
    addSingleDogsDao()
}
