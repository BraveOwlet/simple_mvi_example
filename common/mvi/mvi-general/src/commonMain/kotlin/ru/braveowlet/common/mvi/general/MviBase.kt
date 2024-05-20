package ru.braveowlet.common.mvi.general

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

interface MviBaseModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> :
    MviReducer<Effect, State>,
    MviBootstrap,
    MviActor<Action> {

    val mvi: Mvi<Action, Effect, Event, State>

    override suspend fun invokeBootstrap() = Unit
    override suspend fun invokeActor(action: Action) = Unit
    override fun invokeReducer(effect: Effect, previousState: State): State = previousState

    fun create(
        defaultState: State,
        tag: String,
        logEnable: Boolean,
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
    ) : Mvi<Action, Effect, Event, State> = MviImpl(
        tag = tag,
        logEnable = logEnable,
        defaultState = defaultState,
        scope = scope,
        dispatcher = Dispatchers.Default,
        reducer = this,
        bootstrap = this,
        actor = this,
    )
}

interface MviBaseView
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>{

    @Composable
    fun mvi(): Mvi<Action, Effect, Event, State>

    @Composable
    fun mviContent(
        content: @Composable Mvi<Action, Effect, Event, State>.(state: State) -> Unit
    ) = mvi()
        .run {
            val state by this@run.state.collectAsState()
            content(state)
        }
}

@Composable
inline fun <reified Event : MviEvent> Mvi<*, *, Event, *>.collectEvent(
    crossinline onEvent: suspend CoroutineScope.(Event) -> Unit
) = LaunchedEffect(Unit) {
    this@collectEvent.events.collect {
        onEvent(it)
    }
}
