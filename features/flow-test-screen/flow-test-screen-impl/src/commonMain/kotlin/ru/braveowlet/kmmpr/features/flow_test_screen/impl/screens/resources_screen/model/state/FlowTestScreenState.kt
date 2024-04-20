package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.state

import kotlinx.datetime.Instant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import ru.braveowlet.common.mvi.general.models.MviState

internal data class FlowTestScreenState(
    val isRun: Boolean,
    val value1: List<FlowTestValueState>,
    val value2: List<FlowTestValueState>,
    val resultValue: List<FlowTestResultState>,
) : MviState {

    @OptIn(FormatStringsInDatetimeFormats::class)
    private val formatTimePattern = DateTimeComponents.Format {
        byUnicodePattern("HH:mm:ss[.SSSSSS]")
    }

    fun isRun(isRun: Boolean): FlowTestScreenState = copy(
        isRun = isRun
    )

    fun addResultValue(value1: Int, value2: Int): FlowTestScreenState = copy(
        resultValue = resultValue.plus(
            FlowTestResultState(
                value1 = value1.toString(),
                value2 = value2.toString(),
                isRight = value1 >= value2
            )
        )
    )

    fun clean(): FlowTestScreenState = copy(
        value1 = emptyList(),
        value2 = emptyList(),
        resultValue = emptyList()
    )

    fun addValue1(
        value: Int,
        timeEmit: Instant,
        timeCollect: Instant,
        resultValue: List<FlowTestResultState>,
    ): FlowTestScreenState = copy(
        value1 = value1
            .plus(
                FlowTestValueState(
                    value = value.toString(),
                    timeEmit = timeEmit.format(formatTimePattern),
                    timeCollect = timeCollect.format(formatTimePattern),
                    isRight = false,
                )
            ).map { newValue ->
                newValue.copy(
                    isRight = !resultValue.any { !it.isRight && it.value1 == newValue.value }
                )
            }
    )

    fun addValue2(
        value: Int,
        timeEmit: Instant,
        timeCollect: Instant,
        resultValue: List<FlowTestResultState>,
    ): FlowTestScreenState = copy(
        value2 = value2
            .plus(
                FlowTestValueState(
                    value = value.toString(),
                    timeEmit = timeEmit.format(formatTimePattern),
                    timeCollect = timeCollect.format(formatTimePattern),
                    isRight = false,
                )
            ).map { newValue ->
                newValue.copy(
                    isRight = !resultValue.any { !it.isRight && it.value2 == newValue.value }
                )
            }
    )
}
