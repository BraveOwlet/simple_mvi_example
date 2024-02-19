package ru.braveowlet.kmmpr.common.mvi.general

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
import ru.braveowlet.kmmpr.common.logger.Logger
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

    private val instanceId: String = getRandomInstanceId(4)

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

    override fun log(message: String) {
        if (logEnable) {
            logger.log("$tag [$instanceId]", message)
        }
    }

    override fun logDebug(message: String) =
        logger.log("$tag [$instanceId]", message)


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun mergeBootAndActionEffect(): Flow<Effect?> = merge(
        boot()
            .onEach { log("BOOT EFFECT -> $it") },
        actionsFlow
            .onEach { log("ACTION EFFECT -> $it") }
            .flatMapMerge { actor(it, currentState) },
    )

    private fun Flow<Effect>.produceEvent(): Flow<Effect> = this.onEach { effect ->
        eventFlow.emitAll(
            eventProducer(effect, currentState).onEach { log("EVENT -> $it") }
        )
    }

    private fun Flow<Effect>.reduceState(): Flow<State> = this.map {
        stateReducer(it, currentState).also { newState ->
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
            .reduceState()
            .distinctUntilChanged()
            .stateIn(scope)
            .also { stateFlow = it }

    override suspend fun acceptAction(action: Action) = actionsFlow.emit(action)

    override fun eventFlow(): SharedFlow<Event> = eventFlow

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