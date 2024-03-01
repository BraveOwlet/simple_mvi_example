package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.braveowlet.common.mvi.general.MviActor

internal class MainScreenActor : MviActor<MainScreenAction, MainScreenEffect, MainScreenState> {
    override suspend fun invoke(
        action: MainScreenAction,
        state: MainScreenState
    ): Flow<MainScreenEffect> = flowOf(
        when (action) {
            is MainScreenAction.ClickButtonDogsScreen -> MainScreenEffect.ButtonDogsScreenClicked
            is MainScreenAction.ClickButtonSavedDogsScreen -> MainScreenEffect.ButtonSavedDogsScreenClicked
            is MainScreenAction.ClickButtonResourcesScreen -> MainScreenEffect.ButtonResourcesScreenClicked
        }
    )
}
