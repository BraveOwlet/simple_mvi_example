package ru.braveowlet.common.mvi.general.internal

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
import ru.braveowlet.common.mvi.general.Mvi
import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.common.mvi.general.MviEvent
import ru.braveowlet.common.mvi.general.MviState

internal class MviImpl
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    tag: String,
    logEnable: Boolean,
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    reducer: (Effect, State) -> State ,
    bootstrap: suspend () -> Unit,
    actor: suspend (Action) -> Unit,
) : Mvi<Action, Effect, Event, State> {

    private val logger = MviLogger<Action, Effect, Event, State>(tag, logEnable)

    private val eventChannel: Channel<Event> = Channel(UNLIMITED)
    private val effectChannel: Channel<Effect> = Channel(UNLIMITED)
    private val actionChannel: Channel<Action> = Channel(UNLIMITED)

    private val bufferStateFlow: MutableSharedFlow<State> = MutableSharedFlow(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val eventFlow: Flow<Event> by lazy {
        eventChannel
            .receiveAsFlow()
            .onEach(logger::log)
            .flowOn(dispatcher)
    }

    override val stateFlow: StateFlow<State> by lazy {
        bufferStateFlow
            .onStart { logger.log(defaultState) }
            .onStart {
                scope.launch(dispatcher) {
                    actionChannel
                        .receiveAsFlow()
                        .onEach(logger::log)
                        .onEach { launch { actor(it) } }
                        .catchAndRetry(logger::logActor)
                        .launchIn(this)

                    effectChannel
                        .receiveAsFlow()
                        .onEach(logger::log)
                        .map { reducer(it, stateFlow.value) }
                        .onEach { bufferStateFlow.emit(it) }
                        .onEach(logger::log)
                        .catchAndRetry { logger.logReducer(it) }
                        .launchIn(this)

                    launch {
                        bootstrap()
                        logger.logBootstrap()
                    }
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

private fun <T> Flow<T>.catchAndRetry(
    onError: (Throwable) -> Unit,
): Flow<T> = retryWhen { cause, _ ->
    onError(cause)
    true
}
