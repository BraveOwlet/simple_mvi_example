package ru.braveowlet.common.mvi.impl

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.braveowlet.common.mvi.general.Mvi
import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.common.mvi.general.MviEvent
import ru.braveowlet.common.mvi.general.MviState

abstract class MviModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    tag: String,
    logEnable: Boolean = true,
) : ScreenModel,
    Mvi<Action, Effect, Event, State> {

    private val mvi: Mvi<Action, Effect, Event, State> by lazy {
        Mvi.create(
            tag = tag,
            logEnable = logEnable,
            defaultState = defaultState,
            scope = screenModelScope,
            dispatcher = Dispatchers.Default,
            reducer = ::reducer,
            actor = ::actor,
            bootstrap = ::bootstrap,
        )
    }

    protected open suspend fun bootstrap() = run { }
    protected open suspend fun actor(action: Action) = run { }
    protected open fun reducer(effect: Effect, previousState: State): State = previousState

    override val eventFlow: Flow<Event> get() = mvi.eventFlow
    override val stateFlow: StateFlow<State> get() = mvi.stateFlow
    override fun push(action: Action) = mvi.push(action)
    override fun push(event: Event) = mvi.push(event)
    override fun push(effect: Effect) = mvi.push(effect)
}



