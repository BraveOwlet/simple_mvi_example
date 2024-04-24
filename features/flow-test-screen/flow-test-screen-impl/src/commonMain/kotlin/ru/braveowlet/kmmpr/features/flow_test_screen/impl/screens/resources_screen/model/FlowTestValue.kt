package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

internal data class FlowTestValue(
    val value: Int,
    val time: Instant,
)

internal fun FlowTestValue?.nextValue(): FlowTestValue = FlowTestValue(
    value = (this?.value ?: 0) + 1,
    time = Clock.System.now()
)
