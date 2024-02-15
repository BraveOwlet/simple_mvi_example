package ru.braveowlet.kmmpr.common.mvi.general

import kotlinx.coroutines.flow.Flow

fun interface MviBoot
<Effect : MviEffect> : () -> Flow<Effect> {
    override fun invoke(): Flow<Effect>
}

fun interface MviActor<Action : MviAction, Effect : MviEffect, State : MviState>
    : suspend (Action, State) -> Flow<Effect> {
    override suspend fun invoke(action: Action, state: State): Flow<Effect>
}

fun interface MviEventProducer
<Effect : MviEffect, Event : MviEvent, State : MviState> : suspend (Effect, State) -> Flow<Event> {
    override suspend fun invoke(effect: Effect, state : State): Flow<Event>
}

fun interface MviStateReducer
<Effect : MviEffect, State : MviState> : suspend (Effect, State) -> State {
    override suspend fun invoke(effect: Effect, previousState: State): State
}
