package ru.braveowlet.kmmpr.common.mvi

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

@Composable
expect inline fun <reified Action : MviAction, reified Effect : MviEffect, reified Event : MviEvent, reified State : MviState, reified T : MviViewModel<Action, Effect, Event, State>> withMviViewModel(
    noinline content: @Composable (State, Flow<Event>, (Action) -> Unit) -> Unit
)