package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen

import kotlinx.coroutines.CoroutineScope
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.GetRandomDogUseCase
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.SaveDogUseCase
import ru.braveowlet.kmmpr.core.network.handleMap
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenAction
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenEffect
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenEvent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenState

internal class DogsScreenModel(
    tag: String,
    private val getRandomDogUseCase: GetRandomDogUseCase,
    private val saveDogUseCase: SaveDogUseCase,
) : MviModel<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
    defaultState = DogsScreenState.DEFAULT,
    tag = tag,
) {

    override fun invokeReducer(
        effect: DogsScreenEffect,
        previousState: DogsScreenState
    ): DogsScreenState = when (effect) {
        is DogsScreenEffect.DogLoadFiled -> previousState.clean()
        is DogsScreenEffect.DogLoaded -> previousState.setDog(effect.dog)
    }

    override suspend fun invokeActor(action: DogsScreenAction, scope: CoroutineScope) =
        when (action) {
            is DogsScreenAction.ClickButtonBack ->
                interractor.push(DogsScreenEvent.NavigateToBack)

            is DogsScreenAction.ClickButtonGetDog ->
                interractor.push(processClickButtonGetDog(getRandomDogUseCase))

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