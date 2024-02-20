package ru.braveowlet.kmmpr.components.dogs.domain.usecase

import ru.braveowlet.kmmpr.components.dogs.domain.model.ImageWithDog
import ru.braveowlet.kmmpr.components.dogs.domain.repository.DogsRepository
import ru.braveowlet.kmmpr.core.network.NetworkResult

interface GetRandomImageWithDogsUseCase {
    suspend operator fun invoke(): NetworkResult<ImageWithDog>
}

internal class GetRandomImageWithDogsUseCaseImpl(
    private val dogsRepository: DogsRepository
) : GetRandomImageWithDogsUseCase {

    override suspend fun invoke(): NetworkResult<ImageWithDog> =
        dogsRepository.getRandomImageWithDog()
}
