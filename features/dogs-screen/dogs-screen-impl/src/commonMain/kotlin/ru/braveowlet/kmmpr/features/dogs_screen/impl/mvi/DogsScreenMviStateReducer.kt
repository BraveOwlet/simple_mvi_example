package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviStateReducer

internal class DogsScreenMviStateReducer : MviStateReducer<DogsScreenEffect, DogsScreenState> {
    override suspend fun invoke(
        effect: DogsScreenEffect,
        previousState: DogsScreenState
    ): DogsScreenState = when (effect) {
        is DogsScreenEffect.DogLoaded -> previousState.copy(
            dog = effect.dog
        )

        is DogsScreenEffect.DogLoadFiled -> previousState.copy(
            dog = null
        )

        is DogsScreenEffect.ButtonBackClicked -> previousState
    }
}
