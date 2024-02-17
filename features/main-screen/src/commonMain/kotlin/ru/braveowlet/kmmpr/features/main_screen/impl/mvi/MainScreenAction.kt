package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviAction

sealed interface MainScreenAction : MviAction {
    data object ClickButton : MainScreenAction
}