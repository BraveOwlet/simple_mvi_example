package ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi

import ru.braveowlet.common.mvi.general.MviAction

internal sealed interface MainScreenAction : MviAction {
    data object ClickButtonDogsScreen : MainScreenAction
    data object ClickButtonSavedDogsScreen : MainScreenAction
}
