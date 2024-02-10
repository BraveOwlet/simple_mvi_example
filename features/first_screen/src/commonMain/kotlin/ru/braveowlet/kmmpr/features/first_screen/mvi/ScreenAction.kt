package ru.braveowlet.kmmpr.features.first_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviAction

sealed interface ScreenAction : MviAction {
    data object ClickButton : ScreenAction
}