package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviEvent

internal sealed interface MainScreenEvent : MviEvent {
    data object NavigateToDogsScreen : MainScreenEvent
    data object NavigateToSavedDogsScreen : MainScreenEvent
}