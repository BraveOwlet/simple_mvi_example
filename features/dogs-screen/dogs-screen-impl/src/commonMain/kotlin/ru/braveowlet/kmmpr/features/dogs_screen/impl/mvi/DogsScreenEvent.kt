package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviEvent

internal sealed interface DogsScreenEvent : MviEvent {
    data object NavigateToBack : DogsScreenEvent
    data class ShowError(val message: String?) : DogsScreenEvent
}
