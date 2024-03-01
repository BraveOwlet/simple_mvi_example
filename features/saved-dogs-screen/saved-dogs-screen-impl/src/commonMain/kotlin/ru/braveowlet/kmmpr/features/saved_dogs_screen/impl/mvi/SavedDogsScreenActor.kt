package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.braveowlet.common.mvi.general.MviActor

internal class SavedDogsScreenActor :
    MviActor<SavedDogsScreenAction, SavedDogsScreenEffect, SavedDogsScreenState> {
    override suspend fun invoke(
        action: SavedDogsScreenAction,
        state: SavedDogsScreenState
    ): Flow<SavedDogsScreenEffect> = flowOf<SavedDogsScreenEffect>(
        when (action) {
            is SavedDogsScreenAction.ClickButtonBack -> SavedDogsScreenEffect.ButtonBackClicked
        }
    )
}
