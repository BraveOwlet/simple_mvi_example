package ru.braveowlet.common.mvi.general.api.models

import kotlinx.coroutines.CoroutineScope

interface MviAction

interface MviEffect

interface MviEvent

interface MviState {
    val isLoggable: Boolean get() = true
}

fun interface MviReducer<Effect : MviEffect, State : MviState> {
    fun invokeReducer(effect: Effect, previousState: State): State
}

fun interface MviBootstrap {
    suspend fun invokeBootstrap(scope: CoroutineScope)
    fun MviCoroutineScope.launch(onFinish: (Throwable?) -> Unit) = launch(onFinish) {
        invokeBootstrap(this)
        onFinish(null)
    }
}

fun interface MviActor<Action : MviAction> {
    suspend fun invokeActor(action: Action, scope: CoroutineScope)
    fun MviCoroutineScope.launch(action: Action, onError: (Throwable) -> Unit) = launch(onError) {
        invokeActor(action, this)
    }
}
