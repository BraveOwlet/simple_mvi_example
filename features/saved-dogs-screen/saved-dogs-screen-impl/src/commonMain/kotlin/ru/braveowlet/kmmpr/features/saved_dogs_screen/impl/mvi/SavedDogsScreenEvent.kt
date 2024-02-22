package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviEvent

internal sealed interface SavedDogsScreenEvent : MviEvent {
    data object NavigateToBack : SavedDogsScreenEvent
}