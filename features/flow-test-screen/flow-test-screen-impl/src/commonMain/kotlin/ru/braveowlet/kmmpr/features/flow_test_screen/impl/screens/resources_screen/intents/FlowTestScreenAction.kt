package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.intents

import ru.braveowlet.common.mvi.general.models.MviAction

internal sealed interface FlowTestScreenAction : MviAction {
    data object ClickButtonBack : FlowTestScreenAction
    data class ClickButtonTest(val isRun : Boolean) : FlowTestScreenAction
    data object ClickButtonClean : FlowTestScreenAction
}
