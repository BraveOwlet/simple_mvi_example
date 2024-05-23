package ru.braveowlet.kmmpr.components.dogs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ru.braveowlet.common.logger.Logger
import ru.braveowlet.kmmpr.components.dogs.data.api.DogsApi
import ru.braveowlet.kmmpr.components.dogs.data.dao.DogsDao
import ru.braveowlet.kmmpr.components.dogs.data.dto.DogDto
import ru.braveowlet.kmmpr.components.dogs.data.entity.DogEntity
import ru.braveowlet.kmmpr.components.dogs.data.mapper.toDomain
import ru.braveowlet.kmmpr.components.dogs.data.mapper.toEntity
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import ru.braveowlet.kmmpr.components.dogs.domain.repository.DogsRepository
import ru.braveowlet.kmmpr.core.network.map

internal class DogsRepositoryImpl(
    private val dogsApi: DogsApi,
    private val dogsDao: DogsDao
) : DogsRepository {

    override suspend fun getRandomDog(): Result<Dog> = dogsApi
        .getRandomDog()
        .map(DogDto::toDomain)

    override suspend fun saveDog(dog: Dog) = dogsDao
        .insert(dog.toEntity())

    override suspend fun loadDogs(): List<Dog> = dogsDao
        .get()
        .map(DogEntity::toDomain)

    override fun observeDogs(): Flow<List<Dog>> = dogsDao
        .observe()
        .map { dogs -> dogs.map(DogEntity::toDomain) }
}
