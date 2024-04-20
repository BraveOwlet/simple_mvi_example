package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.intents

import ru.braveowlet.common.mvi.general.models.MviEvent

internal sealed interface DogsScreenEvent : MviEvent {
    data object NavigateToBack : DogsScreenEvent
    data class ShowError(val message: String?) : DogsScreenEvent
}
