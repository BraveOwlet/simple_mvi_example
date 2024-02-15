package ru.braveowlet.kmmpr.common.mvi.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.braveowlet.kmmpr.common.mvi.general.MviAction
import ru.braveowlet.kmmpr.common.mvi.general.MviEffect
import ru.braveowlet.kmmpr.common.mvi.general.MviEvent
import ru.braveowlet.kmmpr.common.mvi.general.MviState
import ru.braveowlet.kmmpr.common.mvi.general.mviBaseScreen

@Composable
inline fun <reified Action : MviAction, reified Effect : MviEffect, reified Event : MviEvent, reified State : MviState, reified T : MviViewModel<Action, Effect, Event, State>> mviKoinScreen(
    noinline content: @Composable (State, Flow<Event>, (Action) -> Unit) -> Unit
) {
    val viewModel = koinViewModel<T>()
    mviBaseScreen(
        scope = viewModel.viewModelScope,
        controller = viewModel.mviController
    ) { stateFlow, eventFlow, acceptAction ->
        val state by stateFlow.collectAsStateWithLifecycle()
        content(state, eventFlow, acceptAction)
    }
}
