package ru.braveowlet.kmmpr.components.dogs.domain.usecase

import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import ru.braveowlet.kmmpr.components.dogs.domain.repository.DogsRepository
import ru.braveowlet.kmmpr.core.network.NetworkResult

interface GetRandomDogUseCase {
    suspend operator fun invoke(): NetworkResult<Dog>
}

internal class GetRandomDogUseCaseImpl(
    private val dogsRepository: DogsRepository
) : GetRandomDogUseCase {

    override suspend fun invoke(): NetworkResult<Dog> =
        dogsRepository.getRandomDog()
}
