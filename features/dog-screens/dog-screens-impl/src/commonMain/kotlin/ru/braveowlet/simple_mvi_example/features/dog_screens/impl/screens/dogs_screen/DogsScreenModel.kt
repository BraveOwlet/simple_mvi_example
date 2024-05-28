package ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen

import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase.GetRandomDogUseCase
import ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase.SaveDogUseCase
import ru.braveowlet.simple_mvi_example.core.network.handleMap
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenAction
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenEffect
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenEvent
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenState

internal class DogsScreenModel(
    tag: String,
    private val getRandomDogUseCase: GetRandomDogUseCase,
    private val saveDogUseCase: SaveDogUseCase,
) : MviModel<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
    defaultState = DogsScreenState.DEFAULT,
    tag = tag,
) {

    override fun reducer(
        effect: DogsScreenEffect,
        previousState: DogsScreenState
    ): DogsScreenState = when (effect) {
        is DogsScreenEffect.DogLoadFiled -> previousState.clean()
        is DogsScreenEffect.DogLoaded -> previousState.setDog(effect.dog)
    }

    override suspend fun actor(action: DogsScreenAction) = when (action) {
        is DogsScreenAction.ClickButtonBack ->
            push(DogsScreenEvent.NavigateToBack)

        is DogsScreenAction.ClickButtonGetDog ->
            push(processClickButtonGetDog(getRandomDogUseCase))

        is DogsScreenAction.ClickButtonSaveDog ->
            saveDogUseCase(action.dog)
    }

    private suspend fun processClickButtonGetDog(
        getRandomDogUseCase: GetRandomDogUseCase,
    ): DogsScreenEffect = getRandomDogUseCase().handleMap(
        onSuccess = { DogsScreenEffect.DogLoaded(it) },
        onError = { DogsScreenEffect.DogLoadFiled(it) }
    )
}
