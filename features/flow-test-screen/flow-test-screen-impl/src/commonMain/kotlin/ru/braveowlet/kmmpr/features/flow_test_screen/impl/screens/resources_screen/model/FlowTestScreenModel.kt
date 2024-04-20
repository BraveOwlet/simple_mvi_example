package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import ru.braveowlet.common.mvi.general.models.MviActor
import ru.braveowlet.common.mvi.general.models.MviBootstrap
import ru.braveowlet.common.mvi.general.models.MviReducer
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.intents.FlowTestScreenAction
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.intents.FlowTestScreenEffect
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.intents.FlowTestScreenEvent
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.models.FlowTestResult
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.state.FlowTestScreenState
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.models.FlowTestValue
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.models.nextValue

internal class FlowTestScreenModel(
    tag: String
) : MviModel<FlowTestScreenAction, FlowTestScreenEffect, FlowTestScreenEvent, FlowTestScreenState>(
    defaultState = FlowTestScreenState(
        isRun = false,
        resultValue = emptyList(),
        value1 = emptyList(),
        value2 = emptyList(),
    ),
    tag = tag,
) {

    private var job: Job? = null
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
    private val flow1: MutableStateFlow<FlowTestValue?> = MutableStateFlow(null)
    private val flow2: MutableStateFlow<FlowTestValue?> = MutableStateFlow(null)
    private val resultFlow: MutableStateFlow<FlowTestResult?> = MutableStateFlow(null)

    override val reducer: MviReducer<FlowTestScreenEffect, FlowTestScreenState> =
        MviReducer { effect, previousState ->
            when (effect) {

                is FlowTestScreenEffect.ChangeStateTest -> previousState.isRun(
                    isRun = effect.isRun
                )

                is FlowTestScreenEffect.AddResult -> previousState.addResultValue(
                    value1 = effect.value1,
                    value2 = effect.value2
                )

                is FlowTestScreenEffect.Clean -> previousState.clean()

                is FlowTestScreenEffect.AddValue1 -> previousState.addValue1(
                    value = effect.value,
                    timeEmit = effect.timeEmit,
                    timeCollect = effect.timeCollect,
                    resultValue = previousState.resultValue,
                )

                is FlowTestScreenEffect.AddValue2 -> previousState.addValue2(
                    value = effect.value,
                    timeEmit = effect.timeEmit,
                    timeCollect = effect.timeCollect,
                    resultValue = previousState.resultValue,
                )
            }
        }

    override val bootstrap: MviBootstrap = MviBootstrap {
        subscribeFlow1Flow2()
        subscribeResultFlow()
    }

    override val actor: MviActor<FlowTestScreenAction> = MviActor { action ->
        when (action) {
            is FlowTestScreenAction.ClickButtonBack ->
                controller.emit(FlowTestScreenEvent.NavigateToBack)

            is FlowTestScreenAction.ClickButtonTest ->
                changeStateFlowTest(action.isRun)

            is FlowTestScreenAction.ClickButtonClean ->
                controller.emit(FlowTestScreenEffect.Clean)
        }
    }

    /*private fun subscribeFlow1Flow2() {
        val flow: MutableStateFlow<Pair<FlowTestValue?, FlowTestValue?>> =
            MutableStateFlow(Pair(null, null))

        flow1
            .filterNotNull()
            .onEach {
                controller.emit(
                    FlowTestScreenEffect.AddValue1(
                        value = it.value,
                        timeEmit = it.time,
                        timeCollect = Clock.System.now()
                    )
                )
            }
            .onEach { newValue ->
                flow.update {
                    it.copy(
                        first = newValue
                    )
                }
            }
            .flowOn(dispatcher)
            .launchIn(screenModelScope)

        flow2
            .filterNotNull()
            .onEach {
                controller.emit(
                    FlowTestScreenEffect.AddValue2(
                        value = it.value,
                        timeEmit = it.time,
                        timeCollect = Clock.System.now()
                    )
                )
            }
            .onEach { newValue ->
                flow.update {
                    it.copy(
                        second = newValue
                    )
                }
            }
            .flowOn(dispatcher)
            .launchIn(screenModelScope)

        flow
            .filterNotNull()
            .onEach {
                it.first?.let { first ->
                    it.second?.let { second ->
                        resultFlow.emit(first.value to second.value)
                    }
                }
            }
            .flowOn(dispatcher)
            .launchIn(screenModelScope)
    }*/

    private fun subscribeFlow1Flow2() = combine(
        flow1
            .filterNotNull()
            .onEach {
                controller.emit(
                    FlowTestScreenEffect.AddValue1(
                        value = it.value,
                        timeEmit = it.time,
                        timeCollect = Clock.System.now()
                    )
                )
            },
        flow2
            .filterNotNull()
            .onEach {
                controller.emit(
                    FlowTestScreenEffect.AddValue2(
                        value = it.value,
                        timeEmit = it.time,
                        timeCollect = Clock.System.now()
                    )
                )
            }
    ) { value1, value2 ->
        resultFlow.emit(FlowTestResult(value1.value, value2.value))
    }.flowOn(dispatcher)
        .launchIn(screenModelScope)


    private fun subscribeResultFlow() = resultFlow
        .filterNotNull()
        .onEach {
            controller.emit(FlowTestScreenEffect.AddResult(it.value1, it.value2))
        }
        .flowOn(dispatcher)
        .launchIn(screenModelScope)


    private fun changeStateFlowTest(isRun: Boolean) {
        controller.emit(FlowTestScreenEffect.ChangeStateTest(isRun))
        job?.cancel()
        if (isRun) {
            job = screenModelScope.launch {
                while (true) {
                    flow1.update { it.nextValue() }
                    //delay(1)
                    flow2.update { it.nextValue() }
                    delay(10)
                }
            }
        }
    }
}
