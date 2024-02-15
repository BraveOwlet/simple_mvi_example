package ru.braveowlet.kmmpr.common.mvi.general

import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
inline fun <reified Action : MviAction, reified Effect : MviEffect, reified Event : MviEvent, reified State : MviState>mviBaseScreen(
    scope: CoroutineScope,
    controller: MviController<Action, Effect, Event, State>,
    content: @Composable (StateFlow<State>, Flow<Event>, (Action) -> Unit)->Unit,
) {
    content(controller.getState(scope), controller.eventFlow()) {
        scope.launch {
            controller.acceptAction(it)
        }
    }
}
