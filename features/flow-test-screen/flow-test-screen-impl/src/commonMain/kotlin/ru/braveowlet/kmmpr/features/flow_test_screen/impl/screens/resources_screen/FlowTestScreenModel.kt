package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock
import ru.braveowlet.common.logger.getThreadName
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.mvi.FlowTestScreenAction
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.mvi.FlowTestScreenEffect
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.mvi.FlowTestScreenEvent
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.FlowTestResult
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.mvi.state.FlowTestScreenState
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.FlowTestValue
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.nextValue

internal class FlowTestScreenModel(
    tag: String
) : MviModel<FlowTestScreenAction, FlowTestScreenEffect, FlowTestScreenEvent, FlowTestScreenState>(
    defaultState = FlowTestScreenState.DEFAULT,
    tag = tag,
    logEnable = false,
    customLogEnable = false,
) {

    private var job: Job? = null

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val dispatcherForRunTest = newSingleThreadContext("FlowRunTest")
    private val flow1: MutableStateFlow<FlowTestValue?> = MutableStateFlow(null)
    private val flow2: MutableStateFlow<FlowTestValue?> = MutableStateFlow(null)
    private val resultFlow: MutableStateFlow<FlowTestResult?> = MutableStateFlow(null)

    override fun invokeReducer(
        effect: FlowTestScreenEffect,
        previousState: FlowTestScreenState
    ): FlowTestScreenState = when (effect) {

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
        )

        is FlowTestScreenEffect.AddValue2 -> previousState.addValue2(
            value = effect.value,
            timeEmit = effect.timeEmit,
            timeCollect = effect.timeCollect,
        )
    }

    override suspend fun invokeBootstrap(scope: CoroutineScope) {
        interractor.log{ "Bootstrap выполнен на потоке -> ${getThreadName()}" }
        scope.subscribeFlow1Flow2()
        scope.subscribeResultFlow()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invokeActor(action: FlowTestScreenAction, scope: CoroutineScope) {
        interractor.log { "Actor выполнен на потоке ${getThreadName()}" }
        when (action) {
            is FlowTestScreenAction.ClickButtonBack -> {
                dispatcherForRunTest.close()
                interractor.push(FlowTestScreenEvent.NavigateToBack)
            }

            is FlowTestScreenAction.ClickButtonTest ->
                changeStateFlowTest(action.isRun)

            is FlowTestScreenAction.ClickButtonClean ->
                interractor.push(FlowTestScreenEffect.Clean)
        }
    }

    private fun CoroutineScope.subscribeFlow1Flow2() = combine(
        flow1
            .filterNotNull()
            .onEach {
                interractor.log { "flow1 onEach на потоке -> ${getThreadName()}" }
                interractor.push(
                    FlowTestScreenEffect.AddValue1(
                        value = it.value,
                        timeEmit = it.time,
                        timeCollect = Clock.System.now()
                    )
                )
                interractor.log{ "flow1 onEach после push на потоке -> ${getThreadName()}" }
            },
        flow2
            .filterNotNull()
            .onEach {
                interractor.log{ "flow2 onEach на потоке -> ${getThreadName()}" }
                interractor.push(
                    FlowTestScreenEffect.AddValue2(
                        value = it.value,
                        timeEmit = it.time,
                        timeCollect = Clock.System.now()
                    )
                )
                interractor.log{ "flow2 onEach после push на потоке -> ${getThreadName()}" }
            }
    ) { value1, value2 ->
        interractor.log{ "combine на потоке -> ${getThreadName()}" }
        resultFlow.emit(FlowTestResult(value1.value, value2.value))
        interractor.log{ "combine после emit на потоке -> ${getThreadName()}" }
    }.flowOn(Dispatchers.Default)
        .launchIn(this@subscribeFlow1Flow2)


    private fun CoroutineScope.subscribeResultFlow() = resultFlow
        .filterNotNull()
        .onEach {
            interractor.log{ "resultFlow onEach на потоке -> ${getThreadName()}" }
            interractor.push(FlowTestScreenEffect.AddResult(it.value1, it.value2))
            interractor.log{ "resultFlow onEach после push на потоке -> ${getThreadName()}" }
        }
        .launchIn(this@subscribeResultFlow)

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun changeStateFlowTest(isRun: Boolean) {
        interractor.log{ "changeStateFlowTest на потоке -> ${getThreadName()}" }
        job?.cancel()
        interractor.push(FlowTestScreenEffect.ChangeStateTest(isRun))
        if (isRun) {
            job = screenModelScope.launch(dispatcherForRunTest) {
                interractor.log{ "changeStateFlowTest launch на потоке -> ${getThreadName()}" }
                launch(Dispatchers.Unconfined) {
                    interractor.log{ "LOG1 changeStateFlowTest launch до delay на потоке-> ${getThreadName()}" }
                    delay(50)
                    interractor.log{ "LOG2 changeStateFlowTest launch после delay на потоке-> ${getThreadName()}" }
                }
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
