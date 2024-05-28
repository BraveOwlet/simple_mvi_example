package ru.braveowlet.simple_mvi_example.features.main_screen.impl.screens.main_screen.mvi

import ru.braveowlet.common.mvi.general.MviEvent

internal sealed interface MainScreenEvent : MviEvent {
    data object NavigateToDogsScreen : MainScreenEvent
    data object NavigateToSavedDogsScreen : MainScreenEvent
}
