package ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.braveowlet.simple_mvi_example.components.dogs.domain.model.Dog
import ru.braveowlet.simple_mvi_example.components.dogs.domain.repository.DogsRepository

interface ObserveRandomDogUseCase {
    operator fun invoke(): Flow<List<Dog>>
}

internal class ObserveRandomDogUseCaseImpl(
    private val dogsRepository: DogsRepository
) : ObserveRandomDogUseCase {

    override fun invoke(): Flow<List<Dog>> =
        dogsRepository.observeDogs()
}
