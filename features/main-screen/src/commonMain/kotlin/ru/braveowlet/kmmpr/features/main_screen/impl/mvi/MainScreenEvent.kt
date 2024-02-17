package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviEvent

sealed interface MainScreenEvent : MviEvent {
    data class ShowMessage(val message: String): MainScreenEvent
}