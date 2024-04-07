package ru.braveowlet.kmmpr.components.dogs.data.dao

import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.core.database.DogEntity
import ru.braveowlet.kmmpr.core.database.Queries
import ru.braveowlet.kmmpr.core.database.asFlowList

class DogsDaoImpl(
    private val queries : Queries
) : DogsDao {

    override fun get(): List<DogEntity> = queries.dogs.selectAll().executeAsList()

    override fun observe() : Flow<List<DogEntity>> = queries.dogs.selectAll().asFlowList()

    override suspend fun insert(entity : DogEntity) = queries.dogs.insert(entity.url)
}
