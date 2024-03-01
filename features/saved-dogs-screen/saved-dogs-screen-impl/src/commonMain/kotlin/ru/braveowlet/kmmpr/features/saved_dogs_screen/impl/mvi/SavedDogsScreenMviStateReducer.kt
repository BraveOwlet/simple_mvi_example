package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviStateReducer

internal class SavedDogsScreenMviStateReducer :
    MviStateReducer<SavedDogsScreenEffect, SavedDogsScreenState> {
    override suspend fun invoke(
        effect: SavedDogsScreenEffect,
        previousState: SavedDogsScreenState
    ): SavedDogsScreenState = when (effect) {
        is SavedDogsScreenEffect.DogsUpdated -> previousState.copy(
            dogs = effect.dogs
        )

        is SavedDogsScreenEffect.ButtonBackClicked -> previousState
    }
}
