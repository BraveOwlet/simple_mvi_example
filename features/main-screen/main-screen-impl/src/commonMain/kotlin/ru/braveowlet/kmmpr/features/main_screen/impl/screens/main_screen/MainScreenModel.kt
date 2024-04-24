package ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen

import kotlinx.coroutines.CoroutineScope
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenAction
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenEffect
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenEvent
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenState

internal class MainScreenModel(
    tag: String
) : MviModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    defaultState = MainScreenState,
    tag = tag,
) {

    override suspend fun invokeActor(action: MainScreenAction, scope: CoroutineScope) =
        when (action) {
            is MainScreenAction.ClickButtonDogsScreen ->
                interractor.push(MainScreenEvent.NavigateToDogsScreen)

            is MainScreenAction.ClickButtonSavedDogsScreen ->
                interractor.push(MainScreenEvent.NavigateToSavedDogsScreen)

            is MainScreenAction.ClickButtonResourcesScreen ->
                interractor.push(MainScreenEvent.NavigateToResourcesScreen)

            is MainScreenAction.ClickButtonFlowTestScreen ->
                interractor.push(MainScreenEvent.NavigateToFlowTestScreen)
        }
}
