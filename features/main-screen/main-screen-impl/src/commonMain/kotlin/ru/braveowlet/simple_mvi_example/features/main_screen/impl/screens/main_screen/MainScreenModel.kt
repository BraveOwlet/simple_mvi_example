package ru.braveowlet.simple_mvi_example.features.main_screen.impl.screens.main_screen

import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.simple_mvi_example.features.main_screen.impl.screens.main_screen.mvi.MainScreenAction
import ru.braveowlet.simple_mvi_example.features.main_screen.impl.screens.main_screen.mvi.MainScreenEffect
import ru.braveowlet.simple_mvi_example.features.main_screen.impl.screens.main_screen.mvi.MainScreenEvent
import ru.braveowlet.simple_mvi_example.features.main_screen.impl.screens.main_screen.mvi.MainScreenState

internal class MainScreenModel(
    tag: String
) : MviModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    defaultState = MainScreenState,
    tag = tag,
) {

    override suspend fun actor(action: MainScreenAction) =
        when (action) {
            is MainScreenAction.ClickButtonDogsScreen ->
                push(MainScreenEvent.NavigateToDogsScreen)

            is MainScreenAction.ClickButtonSavedDogsScreen ->
                push(MainScreenEvent.NavigateToSavedDogsScreen)
        }
}
