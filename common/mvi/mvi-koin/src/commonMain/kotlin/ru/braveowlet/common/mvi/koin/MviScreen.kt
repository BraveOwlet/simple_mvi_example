package ru.braveowlet.common.mvi.koin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.core.qualifier.qualifier
import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.common.mvi.general.MviEvent
import ru.braveowlet.common.mvi.general.MviState

abstract class MviScreen<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    private val tag: String
) : Screen {

    @Composable
    override fun Content() {
        getScreenModel<MviScreenModel<Action, Effect, Event, State>>(qualifier(tag))
            .apply {
                val state by getState(screenModelScope).collectAsState()
                MviContent(state, eventFlow()) { action ->
                    screenModelScope.launch {
                        acceptAction(action)
                    }
                }
            }
    }

    @Composable
    abstract fun MviContent(
        state: State,
        eventFlow: Flow<Event>,
        acceptAction: (Action) -> Unit
    )
}
