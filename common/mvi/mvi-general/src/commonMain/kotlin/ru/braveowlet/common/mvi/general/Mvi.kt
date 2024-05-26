package ru.braveowlet.common.mvi.general

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.braveowlet.common.mvi.general.internal.MviImpl

interface MviAction

interface MviEvent

interface MviEffect

interface MviState {
    val log get() = this.toString()
}

interface Mvi
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> {
    val stateFlow: StateFlow<State>
    val eventFlow: Flow<Event>
    fun push(action: Action)
    fun push(effect: Effect)
    fun push(event: Event)

    companion object {
        fun <Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> build(
            tag: String,
            defaultState: State,
            scope: CoroutineScope,
            dispatcher: CoroutineDispatcher,
            reducer: (Effect, State) -> State = { _, state -> state },
            bootstrap: suspend () -> Unit = {},
            actor: suspend (Action) -> Unit = {},
        ): Mvi<Action, Effect, Event, State> = MviImpl(
            tag = tag,
            defaultState = defaultState,
            scope = scope,
            dispatcher = dispatcher,
            reducer = reducer,
            bootstrap = bootstrap,
            actor = actor,
        )
    }
}
