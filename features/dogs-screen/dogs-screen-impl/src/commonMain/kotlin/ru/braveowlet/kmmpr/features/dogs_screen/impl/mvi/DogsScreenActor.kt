package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.common.mvi.general.MviActor
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.GetRandomDogUseCase
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.SaveDogUseCase
import ru.braveowlet.kmmpr.core.network.handleMap

internal class DogsScreenActor(
    private val getRandomDogUseCase: GetRandomDogUseCase,
    private val saveDogUseCase: SaveDogUseCase,
) : MviActor<DogsScreenAction, DogsScreenEffect, DogsScreenState> {
    override suspend fun invoke(
        action: DogsScreenAction,
        state: DogsScreenState
    ): Flow<DogsScreenEffect> = flow {
        when (action) {
            is DogsScreenAction.ClickButtonBack -> emit(DogsScreenEffect.ButtonBackClicked)

            is DogsScreenAction.ClickButtonGetDog ->
                emit(processClickButtonGetDog(getRandomDogUseCase))

            is DogsScreenAction.ClickButtonSaveDog -> saveDogUseCase(action.dog)
        }
    }
}

private suspend fun processClickButtonGetDog(
    getRandomDogUseCase: GetRandomDogUseCase,
): DogsScreenEffect = getRandomDogUseCase().handleMap(
    onSuccess = { DogsScreenEffect.DogLoaded(it) },
    onError = { DogsScreenEffect.DogLoadFiled(it) }
)
