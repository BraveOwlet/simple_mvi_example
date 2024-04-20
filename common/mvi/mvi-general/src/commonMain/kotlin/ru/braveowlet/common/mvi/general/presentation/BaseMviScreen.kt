package ru.braveowlet.common.mvi.general.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.CoroutineScope
import ru.braveowlet.common.mvi.general.models.Mvi
import ru.braveowlet.common.mvi.general.models.MviAction
import ru.braveowlet.common.mvi.general.models.MviEffect
import ru.braveowlet.common.mvi.general.models.MviEvent
import ru.braveowlet.common.mvi.general.models.MviState

abstract class BaseMviScreen
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    private val tag: String,
    private val content: @Composable Mvi<Action, Effect, Event, State>.(state: State, ) -> Unit
) {

    @Composable
    abstract fun getMvi(key: String): Mvi<Action, Effect, Event, State>

    @Composable
    protected fun mviContent() = getMvi(tag)
        .apply {
            val state by this.state.collectAsState()
            content(state)
        }
}

@Composable
inline fun <reified Event : MviEvent> Mvi<*, *, Event, *>.collectEvent(
    crossinline onEvent: suspend CoroutineScope.(Event) -> Unit
) = LaunchedEffect(Unit) {
    this@collectEvent.events.collect { onEvent(it) }
}

