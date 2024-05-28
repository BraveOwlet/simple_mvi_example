package ru.braveowlet.simple_mvi_example.components.dogs.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.braveowlet.simple_mvi_example.components.dogs.domain.model.Dog

internal interface DogsRepository {
    suspend fun getRandomDog(): Result<Dog>
    suspend fun saveDog(dog: Dog)
    suspend fun loadDogs(): List<Dog>
    fun observeDogs(): Flow<List<Dog>>
}
