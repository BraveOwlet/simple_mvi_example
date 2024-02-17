package ru.braveowlet.kmmpr.common.mvi.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.braveowlet.kmmpr.common.mvi.general.MviAction
import ru.braveowlet.kmmpr.common.mvi.general.MviEffect
import ru.braveowlet.kmmpr.common.mvi.general.MviEvent
import ru.braveowlet.kmmpr.common.mvi.general.MviState

@Composable
inline fun <reified Action : MviAction, reified Effect : MviEffect, reified Event : MviEvent, reified State : MviState, reified T : MviScreenModel<Action, Effect, Event, State>> MviScreen(
    tag: String,
    crossinline content: @Composable (state: State, eventFlow: Flow<Event>, acceptAction: (Action) -> Unit) -> Unit,
): Screen = object : Screen {

    override val key: ScreenKey = tag

    @Composable
    override fun Content() {
        getScreenModel<T>().apply {
            val state by getState(screenModelScope).collectAsState()
            content(state, eventFlow()) { action ->
                screenModelScope.launch {
                    acceptAction(action)
                }
            }
        }
    }
}
