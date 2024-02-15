package ru.braveowlet.kmmpr.common.mvi.di

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.common.mvi.general.MviAction
import ru.braveowlet.kmmpr.common.mvi.general.MviEffect
import ru.braveowlet.kmmpr.common.mvi.general.MviEvent
import ru.braveowlet.kmmpr.common.mvi.general.MviState

@Composable
actual inline fun <reified Action : MviAction, reified Effect : MviEffect, reified Event : MviEvent, reified State : MviState, reified T : MviViewModel<Action, Effect, Event, State>> mviScreen(
    noinline content: @Composable (State, Flow<Event>, (Action) -> Unit) -> Unit
) {
    when (LocalDi.current.getByPlatform()) {
        DiType.KOIN -> mviKoinScreen<Action, Effect, Event, State, T>(content)
        DiType.HILT -> mviHiltScreen<Action, Effect, Event, State, T>(content)
    }
}