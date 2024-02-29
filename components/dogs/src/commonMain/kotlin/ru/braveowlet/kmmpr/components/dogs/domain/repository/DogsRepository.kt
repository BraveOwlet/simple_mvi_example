package ru.braveowlet.kmmpr.components.dogs.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import ru.braveowlet.kmmpr.core.network.NetworkResult
import kotlin.coroutines.CoroutineContext

internal interface DogsRepository {
    suspend fun getRandomDog(): NetworkResult<Dog>
    suspend fun saveDog(dog: Dog)
    suspend fun loadDogs() : List<Dog>
    fun observeDogs( context: CoroutineContext) : Flow<List<Dog>>
}
