package ru.braveowlet.kmmpr.features.main_screen.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.kmmpr.common.mvi.MviActor

class MainScreenActor : MviActor<MainScreenAction, MainScreenEffect, MainScreenState> {
    override suspend fun invoke(action: MainScreenAction, state: MainScreenState): Flow<MainScreenEffect> =
        flow {
            when (action) {
                MainScreenAction.ClickButton -> emit(MainScreenEffect.ButtonClicked)
            }
        }
}
