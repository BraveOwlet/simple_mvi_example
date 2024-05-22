package ru.braveowlet.common.mvi.impl

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.qualifier.named
import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.common.mvi.general.MviEvent
import ru.braveowlet.common.mvi.general.MviState
import ru.braveowlet.common.mvi.general.collectState

interface MviView<Action : MviAction, Event : MviEvent, State : MviState> : Screen {

    @Composable
    override fun Content() = getScreenModel<MviModel<Action, *, Event, State>>(
        qualifier = named(this::class.simpleName.orEmpty())
    ).
        collectState { state: State ->
            content(
                state = state,
                eventFlow = eventFlow,
                pushAction = ::push
            )
        }

    @Composable
    fun content(
        state: State,
        eventFlow: Flow<Event>,
        pushAction: (Action) -> Unit,
    )
}
