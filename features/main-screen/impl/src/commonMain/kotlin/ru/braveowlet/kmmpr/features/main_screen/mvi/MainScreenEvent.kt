package ru.braveowlet.kmmpr.features.main_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviEvent

sealed interface MainScreenEvent : MviEvent {
    data class ShowMessage(val message: String): MainScreenEvent
}