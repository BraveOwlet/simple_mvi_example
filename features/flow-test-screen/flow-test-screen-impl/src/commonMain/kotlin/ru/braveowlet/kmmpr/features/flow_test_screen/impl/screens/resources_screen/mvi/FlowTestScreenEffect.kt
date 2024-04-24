package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.mvi

import kotlinx.datetime.Instant
import ru.braveowlet.common.mvi.general.api.models.MviEffect

internal sealed interface FlowTestScreenEffect : MviEffect {
    data class ChangeStateTest(val isRun : Boolean) : FlowTestScreenEffect
    data class AddValue1(val value : Int, val timeEmit : Instant, val timeCollect : Instant) :
        FlowTestScreenEffect
    data class AddValue2(val value : Int, val timeEmit : Instant, val timeCollect : Instant) :
        FlowTestScreenEffect
    data class AddResult(val value1 : Int, val value2: Int) : FlowTestScreenEffect
    data object Clean : FlowTestScreenEffect
}
