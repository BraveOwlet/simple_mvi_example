package ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.mvi

import ru.braveowlet.common.mvi.general.MviEvent

internal sealed interface DogsScreenEvent : MviEvent {
    data object NavigateToBack : DogsScreenEvent
    data class ShowError(val message: String?) : DogsScreenEvent
}
