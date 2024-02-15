package ru.braveowlet.kmmpr.common.mvi.di

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.common.mvi.general.MviAction
import ru.braveowlet.kmmpr.common.mvi.general.MviEffect
import ru.braveowlet.kmmpr.common.mvi.general.MviEvent
import ru.braveowlet.kmmpr.common.mvi.general.MviState

@Composable
expect inline fun <reified Action : MviAction, reified Effect : MviEffect, reified Event : MviEvent, reified State : MviState, reified T : MviViewModel<Action, Effect, Event, State>>mviScreen(
    diType : DiType? = null,
    noinline content: @Composable (State, Flow<Event>, (Action) -> Unit) -> Unit
)
