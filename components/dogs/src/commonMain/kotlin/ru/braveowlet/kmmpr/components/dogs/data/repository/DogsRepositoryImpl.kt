package ru.braveowlet.kmmpr.components.dogs.data.repository

import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.components.dogs.data.api.DogsApi
import ru.braveowlet.kmmpr.components.dogs.data.mapper.dogEntityToDomain
import ru.braveowlet.kmmpr.components.dogs.data.mapper.toDomain
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import ru.braveowlet.kmmpr.components.dogs.domain.repository.DogsRepository
import ru.braveowlet.kmmpr.core.database.DogsTableQueries
import ru.braveowlet.kmmpr.core.database.asFlowList
import ru.braveowlet.kmmpr.core.network.NetworkResult
import ru.braveowlet.kmmpr.core.network.map

internal class DogsRepositoryImpl(
    private val dogsApi: DogsApi,
    private val dogsTableQueries: DogsTableQueries
) : DogsRepository {

    override suspend fun getRandomDog(): NetworkResult<Dog> = dogsApi
        .getRandomDog()
        .map { it.toDomain() }

    override suspend fun saveDog(dog: Dog) = dogsTableQueries.insert(dog.url)

    override suspend fun loadDogs(): List<Dog> =
        dogsTableQueries.selectAll(::dogEntityToDomain).executeAsList()

    override fun observeDogs(): Flow<List<Dog>> =
        dogsTableQueries.selectAll(::dogEntityToDomain).asFlowList()
}
