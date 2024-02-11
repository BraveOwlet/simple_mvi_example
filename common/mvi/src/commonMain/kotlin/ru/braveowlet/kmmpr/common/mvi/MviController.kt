package ru.braveowlet.kmmpr.common.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

open class MviController
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    val defaultState: State,
    val actor: MviActor<Action, Effect, State>,
    val boot: MviBoot<Effect>,
    val eventProducer: MviEventProducer<Effect, Event, State>,
    val stateProducer: MviStateProducer<Effect, State>,
    val tag: String,
    val logEnable: Boolean = true,
    val logger: MviLogger = MviLoggerDefault,
) : Mvi<Action, Event, State> {

    private var stateFlow: StateFlow<State>? = null

    private val currentState: State
        get() = stateFlow?.value ?: defaultState

    private val eventFlow: MutableSharedFlow<Event> = MutableSharedFlow(
        replay = EVENTS_REPLAY_COUNT,
        extraBufferCapacity = EVENTS_BUFFER_SIZE,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    private val actionsFlow: MutableSharedFlow<Action> = MutableSharedFlow(
        replay = ACTIONS_REPLAY_COUNT,
        extraBufferCapacity = ACTIONS_BUFFER_SIZE,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    private fun log(message: String) {
        if (logEnable) {
            logger.log(tag, message)
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun mergeBootAndActionEffect(): Flow<Effect?> = merge(
        boot()
            .onEach { log("BOOT EFFECT -> $it") },
        actionsFlow
            .onEach { log("ACTION EFFECT -> $it") }
            .flatMapMerge { actor(it, currentState) },
    )

    private fun Flow<Effect>.produceEvent(): Flow<Effect> = this
        .onEach { effect ->
            eventFlow.emitAll(
                eventProducer(effect, currentState).onEach { log("EVENT -> $it") }
            )
        }

    private fun Flow<Effect>.produceState(): Flow<State> = this
        .map {
            stateProducer(it, currentState).also { newState ->
                if (currentState != newState) {
                    log("OLD STATE -> $currentState")
                    log("NEW STATE -> $newState")
                }
            }
        }

    private fun Flow<State>.stateIn(scope: CoroutineScope): StateFlow<State> = this
        .stateIn(
            scope = scope,
            started = SharingStarted.Lazily,
            initialValue = defaultState
        )

    override fun getState(scope: CoroutineScope): StateFlow<State> =
        stateFlow ?: mergeBootAndActionEffect()
            .filterNotNull()
            .produceEvent()
            .produceState()
            .distinctUntilChanged()
            .stateIn(scope)
            .also { stateFlow = it }

    override suspend fun acceptAction(action: Action) {
        logDebug("MVI acceptAction => $action")
        logDebug("MVI actionsFlow.subscriptionCount => ${actionsFlow.subscriptionCount.value}")
        actionsFlow.emit(action)
    }

    override fun eventFlow(): SharedFlow<Event> = eventFlow

    override fun logDebug(message: String) {
        logger.log(tag, message)
    }
}

private const val ACTIONS_REPLAY_COUNT = 0
private const val ACTIONS_BUFFER_SIZE = 10
private const val EVENTS_REPLAY_COUNT = 0
private const val EVENTS_BUFFER_SIZE = 10