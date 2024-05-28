package ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase

import ru.braveowlet.simple_mvi_example.components.dogs.domain.model.Dog
import ru.braveowlet.simple_mvi_example.components.dogs.domain.repository.DogsRepository

interface SaveDogUseCase {
    suspend operator fun invoke(dog: Dog)
}

internal class SaveDogUseCaseImpl(
    private val dogsRepository: DogsRepository
) : SaveDogUseCase {

    override suspend fun invoke(dog: Dog) = dogsRepository.saveDog(dog)
}
