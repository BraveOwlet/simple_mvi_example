package ru.braveowlet.common.mvi.general

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
import ru.braveowlet.common.mvi.general.internal.MviImpl
import ru.braveowlet.common.mvi.general.internal.MviLogger
import kotlin.reflect.KClass

// Objects
interface MviAction

interface MviEvent

interface MviEffect

interface MviState {
    fun getLogString() = this.toString()
}

// Mvi specification
interface Mvi
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> {
    val stateFlow: StateFlow<State>
    val eventFlow: Flow<Event>
    fun push(action: Action)
    fun push(effect: Effect)
    fun push(event: Event)

    companion object {
        fun <Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> create(
            defaultState: State,
            tag: String,
            logEnable: Boolean,
            scope: CoroutineScope,
            dispatcher: CoroutineDispatcher,
            reducer: (Effect, State) -> State,
            bootstrap: suspend () -> Unit,
            actor: suspend (Action) -> Unit,
        ): Mvi<Action, Effect, Event, State> = MviImpl(
            tag = tag,
            logEnable = logEnable,
            defaultState = defaultState,
            scope = scope,
            dispatcher = dispatcher,
            reducer = reducer,
            bootstrap = bootstrap,
            actor = actor,
        )
    }
}
