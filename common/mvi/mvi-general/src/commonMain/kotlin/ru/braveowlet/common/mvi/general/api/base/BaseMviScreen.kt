package ru.braveowlet.common.mvi.general.api.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.CoroutineScope
import ru.braveowlet.common.mvi.general.api.Mvi
import ru.braveowlet.common.mvi.general.api.models.MviAction
import ru.braveowlet.common.mvi.general.api.models.MviEffect
import ru.braveowlet.common.mvi.general.api.models.MviEvent
import ru.braveowlet.common.mvi.general.api.models.MviState

interface BaseMviScreen
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>{

    @Composable
    fun interractor(): Mvi<Action, Effect, Event, State>

    @Composable
    fun mviContent(
        content: @Composable Mvi<Action, Effect, Event, State>.(state: State) -> Unit
    ) = interractor()
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

