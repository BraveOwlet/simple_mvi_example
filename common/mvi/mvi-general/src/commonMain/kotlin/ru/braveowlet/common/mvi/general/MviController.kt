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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.braveowlet.common.logger.Logger
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt

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

    private fun mergeBootAndActionEffect(scope: CoroutineScope) =
        scope.launch(Dispatchers.IO) {
            boot()
                .onEach { log("$LOG_BOOT_EFFECT -> $it") }
                .collect { effectFlow.emit(it) }
            actionsFlow
                .onEach { log("$LOG_ACTION_EFFECT -> $it") }
                .collect {
                    launch {
                        actor(it, currentState).collect {
                            effectFlow.emit(it)
                        }
                    }
                }
        }


    private fun Flow<Effect>.produceEvent(): Flow<Effect> =
        onEach { effect ->
            eventFlow.emitAll(
                eventProducer(effect, currentState).onEach { log("$LOG_EVENT_EFFECT -> $it") }
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

    override fun getState(scope: CoroutineScope): StateFlow<State> =
        stateFlow ?: run {
            mergeBootAndActionEffect(scope)
            effectFlow
                .filterNotNull()
                .produceEvent()
                .reduceState()
                .distinctUntilChanged()
                .stateIn(scope)
                .also { newStateFlow -> stateFlow = newStateFlow }
        }

    override suspend fun acceptAction(action: Action) = actionsFlow.emit(action)

    override fun eventFlow(): SharedFlow<Event> = eventFlow

    override fun log(message: String) {
        if (logEnable) {
            logger.log("$TAG_MVI_PREFIX$tag [$instanceId]", message)
        }
    }

    override fun logDebug(message: String) =
        logger.log("$TAG_MVI_PREFIX$tag [$instanceId]", message)

    private fun getRandomInstanceId(length: Int = DEFAULT_INSTANCE_ID_LENGTH): String {
        val checkedLength = length.takeIf { it > 0 } ?: DEFAULT_INSTANCE_ID_LENGTH
        val maxRange = 10f.pow(checkedLength).toInt()
        var random = Random.nextInt(1..<maxRange).toString()
        val count = checkedLength - random.length
        repeat(count) {
            random = "0$random"
        }
        return random
    }
}

private const val DEFAULT_INSTANCE_ID_LENGTH = 4
private const val ACTIONS_REPLAY_COUNT = 0
private const val ACTIONS_BUFFER_SIZE = 10
private const val EVENTS_REPLAY_COUNT = 0
private const val EVENTS_BUFFER_SIZE = 10
private const val EFFECT_REPLAY_COUNT = 0
private const val EFFECT_BUFFER_SIZE = 10
private const val TAG_MVI_PREFIX = "MVI_"
private const val LOG_BOOT_EFFECT = "BOOT EFFECT"
private const val LOG_ACTION_EFFECT = "ACTION EFFECT"
private const val LOG_EVENT_EFFECT = "EVENT"
private const val LOG_OLD_STATE = "OLD_STATE"
private const val LOG_NEW_STATE = "NEW_STATE"
