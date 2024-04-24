package ru.braveowlet.common.mvi.general.impl

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.channels.getOrElse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import ru.braveowlet.common.mvi.general.api.models.MviAction
import ru.braveowlet.common.mvi.general.api.models.MviCoroutineScope
import ru.braveowlet.common.mvi.general.api.models.MviEffect
import ru.braveowlet.common.mvi.general.api.models.MviEvent
import ru.braveowlet.common.mvi.general.api.MviInterractor
import ru.braveowlet.common.mvi.general.api.models.MviActor
import ru.braveowlet.common.mvi.general.api.models.MviBootstrap
import ru.braveowlet.common.mvi.general.api.models.MviReducer
import ru.braveowlet.common.mvi.general.api.models.MviState
import ru.braveowlet.common.mvi.general.api.models.flowOn
import ru.braveowlet.common.mvi.general.api.models.launchIn

internal class MviInterractorImpl
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    private val defaultState: State,
    tag: String,
    logEnable: Boolean,
    private val customLogEnable: Boolean,
    private val reducer: MviReducer<Effect, State>,
    private val bootstrap: MviBootstrap,
    private val actor: MviActor<Action>,
    private val coroutineScope: MviCoroutineScope,
) : MviInterractor<Action, Effect, Event, State>,
    MviLogger<Action, Effect, Event, State>(tag, logEnable) {

    private val eventChannel: Channel<Event> = Channel(UNLIMITED)
    private val effectChannel: Channel<Effect> = Channel(UNLIMITED)
    private val actionChannel: Channel<Action> = Channel(UNLIMITED)

    private val stateFlow: MutableSharedFlow<State> = MutableSharedFlow(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private var currentState: State = defaultState

    private fun init() {
        bootstrap
            .run { coroutineScope.launch(::logErrorBootstrap) }

        actionChannel
            .receiveAsFlow()
            .onEach(::log)
            .onEach { actor.run { coroutineScope.launch(it, ::logErrorActor) } }
            .catch { logErrorActor(it) }
            .launchIn(coroutineScope, ::logErrorActor)

        effectChannel
            .receiveAsFlow()
            .onEach(::log)
            .map { reducer.invokeReducer(it, currentState) }
            .onEach { currentState = it; stateFlow.emit(it) }
            .onEach(::log)
            .catch { logErrorReducer(it) }
            .launchIn(coroutineScope, ::logErrorReducer)
    }

    override val events: Flow<Event>
        get() = eventChannel
            .receiveAsFlow()
            .onEach(::log)
            .catch { logErrorEvent(it) }
            .flowOn(coroutineScope)

    override val state: StateFlow<State> by lazy {
        stateFlow
            .onStart { init() }
            .onStart { log(defaultState) }
            .distinctUntilChanged()
            .catch { logErrorState(it) }
            .flowOn(coroutineScope)
            .stateIn(coroutineScope.scope, SharingStarted.Lazily, defaultState)
    }

    override fun push(action: Action) = actionChannel.trySend(action).getOrElse(::logErrorActor)
    override suspend fun push(effect: Effect) = effectChannel.send(effect)
    override suspend fun push(event: Event) = eventChannel.send(event)
    override fun log(message: () -> String) = customLog(customLogEnable, message)
}
