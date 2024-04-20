package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.intents

import ru.braveowlet.common.mvi.general.models.MviEvent

internal sealed interface FlowTestScreenEvent : MviEvent {
    data object NavigateToBack : FlowTestScreenEvent
}
