package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviStateReducer

internal class SavedDogsScreenMviStateReducer : MviStateReducer<SavedDogsScreenEffect, SavedDogsScreenState> {
    override suspend fun invoke(
        effect: SavedDogsScreenEffect,
        previousState: SavedDogsScreenState
    ): SavedDogsScreenState = previousState
}