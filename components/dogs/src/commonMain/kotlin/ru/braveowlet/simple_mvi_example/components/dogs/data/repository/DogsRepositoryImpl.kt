package ru.braveowlet.simple_mvi_example.components.dogs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.braveowlet.simple_mvi_example.components.dogs.data.api.DogsApi
import ru.braveowlet.simple_mvi_example.components.dogs.data.dto.DogDto
import ru.braveowlet.simple_mvi_example.components.dogs.data.mapper.toDomain
import ru.braveowlet.simple_mvi_example.components.dogs.data.mapper.toEntity
import ru.braveowlet.simple_mvi_example.components.dogs.domain.model.Dog
import ru.braveowlet.simple_mvi_example.components.dogs.domain.repository.DogsRepository
import ru.braveowlet.simple_mvi_example.core.database.table.dogs.DogEntity
import ru.braveowlet.simple_mvi_example.core.database.table.dogs.DogsDao
import ru.braveowlet.simple_mvi_example.core.network.map

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
