package ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi

import ru.braveowlet.common.mvi.general.api.models.MviEvent

internal sealed interface ResourcesScreenEvent : MviEvent {
    data object NavigateToBack : ResourcesScreenEvent
}
