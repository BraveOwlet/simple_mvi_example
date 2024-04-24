package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi

import ru.braveowlet.common.mvi.general.api.models.MviEvent

internal sealed interface SavedDogsScreenEvent : MviEvent {
    data object NavigateToBack : SavedDogsScreenEvent
}
