package ru.braveowlet.kmmpr.components.dogs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.braveowlet.kmmpr.components.dogs.data.api.DogsApi
import ru.braveowlet.kmmpr.components.dogs.data.dao.DogsDao
import ru.braveowlet.kmmpr.components.dogs.data.mapper.toDomain
import ru.braveowlet.kmmpr.components.dogs.data.mapper.toEntity
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import ru.braveowlet.kmmpr.components.dogs.domain.repository.DogsRepository
import ru.braveowlet.kmmpr.core.network.NetworkResult
import ru.braveowlet.kmmpr.core.network.map

internal class DogsRepositoryImpl(
    private val dogsApi: DogsApi,
    private val dogsDao: DogsDao
) : DogsRepository {

    override suspend fun getRandomDog(): NetworkResult<Dog> = dogsApi
        .getRandomDog()
        .map { it.toDomain() }

    override suspend fun saveDog(dog: Dog) = dogsDao.insert(dog.toEntity())

    override suspend fun loadDogs(): List<Dog> = dogsDao.get().map { it.toDomain() }

    override fun observeDogs(): Flow<List<Dog>> = dogsDao.observe().map { dogs ->
        dogs.map { it.toDomain() }
    }
}
