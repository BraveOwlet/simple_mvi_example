package ru.braveowlet.common.mvi.general

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import ru.braveowlet.common.logger.Logger

open class MviController
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    val defaultState: State,
    val actor: MviActor<Action, Effect, State>,
    val boot: MviBoot<Effect>,
    val eventProducer: MviEventProducer<Effect, Event, State>,
    val stateReducer: MviStateReducer<Effect, State>,
    val tag: String,
    val logEnable: Boolean,
    val logger: Logger,
) : Mvi<Action, Event, State> {

    private val dispatcher = Dispatchers.IO

    private val instanceId: String = getRandomInstanceId(DEFAULT_INSTANCE_ID_LENGTH)

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

    private val effectFlow: MutableSharedFlow<Effect> = MutableSharedFlow(
        replay = EFFECT_REPLAY_COUNT,
        extraBufferCapacity = EFFECT_BUFFER_SIZE,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    override fun log(message: String) {
        if (logEnable) {
            logger.log("$TAG_MVI_PREFIX$tag [$instanceId]", message)
        }
    }

    override fun logDebug(message: String) =
        logger.log("$TAG_MVI_PREFIX$tag [$instanceId]", message)

    override fun getState(scope: CoroutineScope): StateFlow<State> =
        stateFlow ?: run {
            effectFlow
                .onStart { mergeBootAndActionEffect(scope) }
                .filterNotNull()
                .produceEvent()
                .logCatch(LOG_PRODUCE_EVENT_ERROR, ::log)
                .reduceState()
                .logCatch(LOG_REDUCE_STATE_ERROR, ::log)
                .distinctUntilChanged()
                .stateIn(scope)
                .also { newStateFlow ->
                    stateFlow = newStateFlow
                }
        }

    override suspend fun acceptAction(action: Action) = actionsFlow.emit(action)

    override fun eventFlow(): SharedFlow<Event> = eventFlow

    private fun mergeBootAndActionEffect(scope: CoroutineScope) {
        boot()
            .logCatch(LOG_BOOT_ERROR, ::log)
            .logEach(LOG_BOOT_EFFECT, ::log)
            .onEach { effectFlow.emit(it) }
            .launchIn(scope, dispatcher)

        actionsFlow
            .logEach(LOG_ACTION_EFFECT, ::log)
            .onEach { action ->
                actor(action, currentState)
                    .logCatch(LOG_ACTION_ERROR, ::log)
                    .onEach { effectFlow.emit(it) }
                    .launchIn(scope, dispatcher)
            }
            .launchIn(scope, dispatcher)
    }

    private fun Flow<Effect>.produceEvent(): Flow<Effect> =
        onEach { effect ->
            eventFlow.emitAll(
                eventProducer(effect, currentState).logEach(LOG_EVENT_EFFECT, ::log)
            )
        }

    private fun Flow<Effect>.reduceState(): Flow<State> =
        map {
            stateReducer(it, currentState).also { newState ->
                if (currentState != newState) {
                    log("$LOG_OLD_STATE -> $currentState")
                    log("$LOG_NEW_STATE -> $newState")
                }
            }
        }

    private fun Flow<State>.stateIn(scope: CoroutineScope): StateFlow<State> =
        stateIn(
            scope = scope,
            started = SharingStarted.Lazily,
            initialValue = defaultState
        )
}

private const val DEFAULT_INSTANCE_ID_LENGTH = 4U
private const val ACTIONS_REPLAY_COUNT = 0
private const val ACTIONS_BUFFER_SIZE = 10
private const val EVENTS_REPLAY_COUNT = 0
private const val EVENTS_BUFFER_SIZE = 10
private const val EFFECT_REPLAY_COUNT = 0
private const val EFFECT_BUFFER_SIZE = 10
private const val TAG_MVI_PREFIX = "MVI_"
private const val LOG_BOOT_EFFECT = "BOOT EFFECT"
private const val LOG_BOOT_ERROR = "BOOT ERROR"
private const val LOG_ACTION_EFFECT = "ACTION EFFECT"
private const val LOG_ACTION_ERROR = "ACTION ERROR"
private const val LOG_PRODUCE_EVENT_ERROR = "PRODUCE_EVENT_ERROR"
private const val LOG_REDUCE_STATE_ERROR = "REDUCE_STATE_ERROR"
private const val LOG_EVENT_EFFECT = "EVENT"
private const val LOG_OLD_STATE = "OLD_STATE"
private const val LOG_NEW_STATE = "NEW_STATE"
