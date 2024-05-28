package ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase

import ru.braveowlet.simple_mvi_example.components.dogs.domain.model.Dog
import ru.braveowlet.simple_mvi_example.components.dogs.domain.repository.DogsRepository

interface GetRandomDogUseCase {
    suspend operator fun invoke(): Result<Dog>
}

internal class GetRandomDogUseCaseImpl(
    private val dogsRepository: DogsRepository
) : GetRandomDogUseCase {

    override suspend fun invoke(): Result<Dog> =
        dogsRepository.getRandomDog()
}
