package ru.braveowlet.common.mvi.general

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.channels.getOrElse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.braveowlet.common.mvi.general.internal.MviLogger

interface Mvi
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> {
    val state: StateFlow<State>
    val events: Flow<Event>
    fun push(action: Action)
    fun push(effect: Effect)
    fun push(event: Event)
}

class MviImpl
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    tag: String,
    logEnable: Boolean,
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    reducer: MviReducer<Effect, State>,
    bootstrap: MviBootstrap,
    actor: MviActor<Action>,
) : Mvi<Action, Effect, Event, State> {

    private val logger = MviLogger<Action, Effect, Event, State>(tag, logEnable)

    private val eventChannel: Channel<Event> = Channel(UNLIMITED)
    private val effectChannel: Channel<Effect> = Channel(UNLIMITED)
    private val actionChannel: Channel<Action> = Channel(UNLIMITED)

    private val stateFlow: MutableSharedFlow<State> = MutableSharedFlow(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val events: Flow<Event> by lazy {
        eventChannel
            .receiveAsFlow()
            .onEach(logger::log)
            .flowOn(dispatcher)
    }

    override val state: StateFlow<State> by lazy {
        stateFlow
            .onStart { logger.log(defaultState) }
            .onStart {
                scope.launch(dispatcher) {
                    actionChannel
                        .receiveAsFlow()
                        .onEach(logger::log)
                        .onEach { launch { actor.invokeActor(it) } }
                        .catchAndRetry(logger::logActor)
                        .launchIn(this)

                    effectChannel
                        .receiveAsFlow()
                        .onEach(logger::log)
                        .map { reducer.invokeReducer(it, state.value) }
                        .onEach { stateFlow.emit(it) }
                        .onEach(logger::log)
                        .catchAndRetry { logger.logReducer(it) }
                        .launchIn(this)

                    bootstrap.launchIn(this, logger::logBootstrap)
                }
            }
            .distinctUntilChanged()
            .flowOn(dispatcher)
            .stateIn(scope, SharingStarted.Lazily, defaultState)
    }

    override fun push(action: Action) = actionChannel
        .trySend(action)
        .getOrElse { logger.log(action, it) }

    override fun push(effect: Effect) = effectChannel
        .trySend(effect)
        .getOrElse { logger.log(effect, it) }

    override fun push(event: Event) = eventChannel
        .trySend(event)
        .getOrElse { logger.log(event, it) }
}

private fun MviBootstrap.launchIn(
    coroutineScope: CoroutineScope,
    onFinish: (Throwable?) -> Unit,
) = coroutineScope.launch {
    try {
        invokeBootstrap()
        onFinish(null)
    } catch (t: Throwable) {
        onFinish(t)
    }
}

private fun <T> Flow<T>.catchAndRetry(
    onError: (Throwable) -> Unit,
): Flow<T> = retryWhen { cause, _ ->
    onError(cause)
    true
}
