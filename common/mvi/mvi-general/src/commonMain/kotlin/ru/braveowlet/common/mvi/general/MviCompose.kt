package ru.braveowlet.common.mvi.general

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.Flow

@Composable
fun <Action : MviAction, Event : MviEvent, State : MviState> Mvi<Action, *, Event, State>.collectState(
    onEach: @Composable Mvi<Action, *, Event, State>.(State) -> Unit
) {
    val state by stateFlow.collectAsState()
    onEach(state)
}

@Composable
inline fun <reified Event : MviEvent> Flow<Event>.collectEvent(
    crossinline onEvent: suspend (Event) -> Unit
) = LaunchedEffect(Unit) {
    collect {
        onEvent(it)
    }
}
