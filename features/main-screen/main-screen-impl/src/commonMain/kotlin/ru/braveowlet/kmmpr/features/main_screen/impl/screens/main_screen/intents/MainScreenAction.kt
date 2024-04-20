package ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.intents

import ru.braveowlet.common.mvi.general.models.MviAction

internal sealed interface MainScreenAction : MviAction {
    data object ClickButtonDogsScreen : MainScreenAction
    data object ClickButtonSavedDogsScreen : MainScreenAction
    data object ClickButtonResourcesScreen : MainScreenAction
}