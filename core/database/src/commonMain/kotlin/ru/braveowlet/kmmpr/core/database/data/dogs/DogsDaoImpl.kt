package ru.braveowlet.kmmpr.core.database.data.dogs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.braveowlet.kmmpr.core.database.DogTableQueries
import ru.braveowlet.kmmpr.core.database.domain.asFlowList
import ru.braveowlet.kmmpr.core.database.domain.dogs.DogEntity
import ru.braveowlet.kmmpr.core.database.domain.dogs.DogsDao

internal class DogsDaoImpl(
    private val queries : DogTableQueries
) : DogsDao {

    override fun get(): List<DogEntity> = queries
        .selectAll()
        .executeAsList()
        .map{ it.toEntity() }

    override fun observe() : Flow<List<DogEntity>> = queries
        .selectAll()
        .asFlowList()
        .map {
        it.map { table -> table.toEntity() }
    }

    override suspend fun insert(entity : DogEntity) = queries
        .insert(entity.url)
}
