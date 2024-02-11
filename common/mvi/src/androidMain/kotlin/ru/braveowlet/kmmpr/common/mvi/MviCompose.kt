package ru.braveowlet.kmmpr.common.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
actual inline fun <reified Action : MviAction, reified Effect : MviEffect, reified Event : MviEvent, reified State : MviState, reified T : MviViewModel<Action, Effect, Event, State>> withMviViewModel(
    noinline content: @Composable (State, Flow<Event>, (Action) -> Unit) -> Unit
) {
    val viewModel = koinViewModel<T>()

    val state by viewModel.mviController.getState(viewModel.viewModelScope)
        .collectAsStateWithLifecycle()

    content(state, viewModel.mviController.eventFlow()) {
        viewModel.viewModelScope.launch {
            viewModel.mviController.acceptAction(it)
        }
    }
}
