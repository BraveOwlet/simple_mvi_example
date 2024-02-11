package ru.braveowlet.kmmpr.common.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
actual inline fun <reified Action : MviAction, reified Effect : MviEffect, reified Event : MviEvent, reified State : MviState, reified T : MviViewModel<Action, Effect, Event, State>> withMviViewModel(
    noinline content: @Composable (State, Flow<Event>, (Action) -> Unit) -> Unit
) {
    val viewModel = koinInject<T>()

    val scope = rememberCoroutineScope()

    val state by viewModel.mviController.getState(scope).collectAsState()

    content(state, viewModel.mviController.eventFlow()) {
        scope.launch {
            viewModel.mviController.acceptAction(it)
        }
    }
}