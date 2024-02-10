package ru.braveowlet.kmmpr.features.first_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviEvent

sealed interface ScreenEvent : MviEvent {
    data class ShowMessage(val message: String): ScreenEvent
}