package ru.braveowlet.common.mvi.general.models

import kotlinx.coroutines.CoroutineScope

open class MviReducer<Effect : MviEffect, State : MviState>(
    open val invoke: suspend (effect: Effect, previousState: State) -> State = { _, state -> state }
)

open class MviBootstrap(
    open val invoke: suspend CoroutineScope.() -> Unit = {}
)

open class MviActor<Action : MviAction>(
    open val invoke: suspend CoroutineScope.(action: Action) -> Unit = {}
)
