package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import ru.braveowlet.common.mvi.general.MviBoot
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.ObserveRandomDogUseCase

internal class SavedDogsScreenBoot(
    private val observeRandomDogUseCase: ObserveRandomDogUseCase,
) : MviBoot<SavedDogsScreenEffect> {
    override fun invoke(): Flow<SavedDogsScreenEffect> = merge(
        observeRandomDogUseCase()
            .map { SavedDogsScreenEffect.DogsUpdated(it) },
    )
}
