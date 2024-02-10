package ru.braveowlet.kmmpr.features.first_screen.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.kmmpr.common.mvi.MviActor

class ScreenActor : MviActor<ScreenAction, ScreenEffect, ScreenState> {
    override suspend fun invoke(action: ScreenAction, state: ScreenState): Flow<ScreenEffect> =
        flow {
            when (action) {
                ScreenAction.ClickButton -> emit(ScreenEffect.ButtonClicked)
            }
        }
}
