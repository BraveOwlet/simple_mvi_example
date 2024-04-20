package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.state

internal data class FlowTestValueState(
    val value: String,
    val timeEmit: String,
    val timeCollect: String,
    val isRight: Boolean,
) {
    override fun toString(): String = "$value -> e[${timeEmit}] => c[${timeCollect}]"
}