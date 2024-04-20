package ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents

import ru.braveowlet.common.mvi.general.models.MviEvent

internal sealed interface ResourcesScreenEvent : MviEvent {
    data object NavigateToBack : ResourcesScreenEvent
}
