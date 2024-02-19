package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.kmmpr.common.mvi.general.MviEventProducer

internal class SavedDogsScreenMviEventProducer :
    MviEventProducer<SavedDogsScreenEffect, SavedDogsScreenEvent, SavedDogsScreenState> {
    override suspend fun invoke(
        effect: SavedDogsScreenEffect,
        state: SavedDogsScreenState
    ): Flow<SavedDogsScreenEvent> = flow {
        when (effect) {
            is SavedDogsScreenEffect.ButtonBackClicked -> emit(SavedDogsScreenEvent.NavigateToBack)
        }
    }
}