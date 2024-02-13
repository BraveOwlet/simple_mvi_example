package ru.braveowlet.kmmpr.features.main_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviAction

sealed interface MainScreenAction : MviAction {
    data object ClickButton : MainScreenAction
}