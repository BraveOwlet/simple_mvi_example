package ru.braveowlet.common.mvi.general

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import ru.braveowlet.common.logger.Logger
import ru.braveowlet.common.mvi.general.models.Mvi
import ru.braveowlet.common.mvi.general.models.MviAction
import ru.braveowlet.common.mvi.general.models.MviActor
import ru.braveowlet.common.mvi.general.models.MviBootstrap
import ru.braveowlet.common.mvi.general.models.MviEffect
import ru.braveowlet.common.mvi.general.models.MviEvent
import ru.braveowlet.common.mvi.general.models.MviReducer
import ru.braveowlet.common.mvi.general.models.MviState
import ru.braveowlet.common.mvi.general.utils.addMessage
import ru.braveowlet.common.mvi.general.utils.asErrorTag
import ru.braveowlet.common.mvi.general.utils.asMviTag
import ru.braveowlet.common.mvi.general.utils.getRandomInstanceId
import ru.braveowlet.common.mvi.general.utils.launchWithLogException

open class MviController
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    private val defaultState: State,
    private val reducer: MviReducer<Effect, State>,
    private val bootstrap: MviBootstrap,
    private val actor: MviActor<Action>,
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher,
    private val tag: String,
    private val logEnable: Boolean,
    private val logger: Logger,
) : Mvi<Action, Effect, Event, State> {

    private val instanceId: String = getRandomInstanceId()

    private val effectFlow: MutableSharedFlow<Effect> = MutableSharedFlow(
        replay = EFFECT_REPLAY_COUNT,
        extraBufferCapacity = EFFECT_BUFFER_SIZE,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

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

    override val state: StateFlow<State> by lazy {
        effectFlow
            .onStart {
                startBootstrap(bootstrap)
                subscribeActionsFlow(actor)
            }
            .onEach { log(EFFECT_TAG.addMessage(it)) }
            .mapToState(reducer)
            .filterStateFlow()
            .flowOn(dispatcher)
            .stateIn(
                scope = scope,
                started = SharingStarted.Lazily,
                initialValue = defaultState
            )
    }

    override val events: Flow<Event> get() = eventFlow.onEach {
        log(EVENT_TAG.addMessage(it))
    }

    override fun emit(action: Action) {
        scope.launchWithLogException(ACTION_TAG.asErrorTag()) {
            actionsFlow.emit(action)
        }
    }

    override fun emit(effect: Effect) {
        scope.launchWithLogException(EFFECT_TAG.asErrorTag()) {
            effectFlow.emit(effect)
        }
    }

    override fun emit(event: Event) {
        scope.launchWithLogException(EVENT_TAG.asErrorTag()) {
            eventFlow.emit(event)
        }
    }

    override fun log(message: String, isDebug: Boolean) {
        if (isDebug || logEnable) {
            logger.log(tag.asMviTag(instanceId), message)
        }
    }

    private fun Flow<Effect>.mapToState(
        reducer: MviReducer<Effect, State>
    ): Flow<State> = this
        .map { effect ->
            val currentState = state.value
            val newState = reducer.invoke(effect, currentState)
            currentState to newState
        }
        .catch { log(REDUCER_TAG.asErrorTag().addMessage(it)) }
        .filter { it.first != it.second }
        .onEach {
            log("${OLD_STATE_TAG.addMessage(it.first)}\n${NEW_STATE_TAG.addMessage(it.second)}")
        }
        .map { it.second }

    private fun startBootstrap(bootstrap: MviBootstrap) {
        scope.launchWithLogException(BOOTSTRAP_TAG.asErrorTag()) {
            log(BOOTSTRAP_TAG)
            bootstrap.run { invoke() }
        }
    }

    private fun subscribeActionsFlow(actor: MviActor<Action>) {
        scope.launchWithLogException(ACTOR_TAG.asErrorTag()) {
            actionsFlow.collect { action ->
                log(ACTION_TAG.addMessage(action))
                launchWithLogException(ACTOR_TAG.asErrorTag()) {
                    actor.run { invoke(action) }
                }
            }
        }
    }

    private fun Flow<State>.filterStateFlow(): Flow<State> = this
        .filterNotNull()
        .distinctUntilChanged()
        .catch { log(UNKNOWN_ERROR_TAG.addMessage(it)) }

    private fun CoroutineScope.launchWithLogException(
        tag: String,
        block: suspend CoroutineScope.() -> Unit
    ) = launchWithLogException(
        tag = tag,
        dispatcher = dispatcher,
        onError = ::log,
        block = block
    )

    companion object {
        private const val ACTIONS_REPLAY_COUNT = 0
        private const val ACTIONS_BUFFER_SIZE = 10
        private const val EVENTS_REPLAY_COUNT = 0
        private const val EVENTS_BUFFER_SIZE = 10
        private const val EFFECT_REPLAY_COUNT = 0
        private const val EFFECT_BUFFER_SIZE = 10
        private const val ACTION_TAG = "ACTION"
        private const val EFFECT_TAG = "EFFECT"
        private const val EVENT_TAG = "EVENT"
        private const val BOOTSTRAP_TAG = "BOOTSTRAP"
        private const val ACTOR_TAG = "ACTOR"
        private const val REDUCER_TAG = "ACTOR"
        private const val OLD_STATE_TAG = "OLD_STATE"
        private const val NEW_STATE_TAG = "NEW_STATE"
        private const val UNKNOWN_ERROR_TAG = "UNKNOWN_ERROR"
    }
}
