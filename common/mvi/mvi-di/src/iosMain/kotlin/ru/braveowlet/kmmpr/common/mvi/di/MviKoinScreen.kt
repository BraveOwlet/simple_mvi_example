package ru.braveowlet.kmmpr.common.mvi.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.flow.Flow
import org.koin.compose.koinInject
import ru.braveowlet.kmmpr.common.mvi.general.MviAction
import ru.braveowlet.kmmpr.common.mvi.general.MviEffect
import ru.braveowlet.kmmpr.common.mvi.general.MviEvent
import ru.braveowlet.kmmpr.common.mvi.general.MviState
import ru.braveowlet.kmmpr.common.mvi.general.mviBaseScreen

@Composable
inline fun <reified Action : MviAction, reified Effect : MviEffect, reified Event : MviEvent, reified State : MviState, reified T : MviViewModel<Action, Effect, Event, State>> mviKoinScreen(
    noinline content: @Composable (State, Flow<Event>, (Action) -> Unit) -> Unit
) {
    val scope = rememberCoroutineScope()
    val viewModel = koinInject<T>()
    mviBaseScreen(
        scope = scope,
        controller = viewModel.mviController
    ) { stateFlow, eventFlow, acceptAction ->
        val state by stateFlow.collectAsState()
        content(state, eventFlow, acceptAction)
    }
}