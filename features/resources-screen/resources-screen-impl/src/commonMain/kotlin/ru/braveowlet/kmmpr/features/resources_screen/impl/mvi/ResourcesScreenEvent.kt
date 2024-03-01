package ru.braveowlet.kmmpr.features.resources_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviEvent

internal sealed interface ResourcesScreenEvent : MviEvent {
    data object NavigateToBack : ResourcesScreenEvent
}
