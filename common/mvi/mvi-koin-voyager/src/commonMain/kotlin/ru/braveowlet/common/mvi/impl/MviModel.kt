package ru.braveowlet.common.mvi.impl

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersHolder
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import ru.braveowlet.common.mvi.general.Mvi
import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.common.mvi.general.MviEvent
import ru.braveowlet.common.mvi.general.MviState
import ru.braveowlet.common.utils.simpleNameOrThrow

abstract class MviModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    tag: String,
) : ScreenModel, Mvi<Action, Effect, Event, State> {

    private val mvi: Mvi<Action, Effect, Event, State> by lazy {
        Mvi.build(
            tag = tag,
            defaultState = defaultState,
            scope = screenModelScope,
            reducer = ::reducer,
            actor = ::actor,
            bootstrap = ::bootstrap,
        )
    }

    final override val eventFlow: Flow<Event> get() = mvi.eventFlow
    final override val stateFlow: StateFlow<State> get() = mvi.stateFlow
    final override fun push(action: Action) = mvi.push(action)
    final override fun push(event: Event) = mvi.push(event)
    final override fun push(effect: Effect) = mvi.push(effect)
    protected open suspend fun bootstrap() = run { }
    protected open suspend fun actor(action: Action) = run { }
    protected open fun reducer(effect: Effect, previousState: State): State = previousState
}

inline fun <reified T : MviView<*, *, *>> Module.provideMviModel(
    crossinline factoryMviModel: Scope.(tag: String, params: ParametersHolder) -> MviModel<*, *, *, *>,
) = T::class.simpleNameOrThrow.let { tag ->
    factory<MviModel<*, *, *, *>>(named(tag)) { params -> factoryMviModel(tag, params) }
}
