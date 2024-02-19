package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviAction

internal sealed interface MainScreenAction : MviAction {
    data object ClickButtonDogsScreen : MainScreenAction
    data object ClickButtonSavedDogsScreen : MainScreenAction
}