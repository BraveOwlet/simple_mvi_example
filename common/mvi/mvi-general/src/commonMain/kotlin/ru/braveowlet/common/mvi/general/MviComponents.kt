package ru.braveowlet.common.mvi.general

fun interface MviReducer<Effect : MviEffect, State : MviState> {
    fun invokeReducer(effect: Effect, previousState: State): State
}

fun interface MviBootstrap {
    suspend fun invokeBootstrap()
}

fun interface MviActor<Action : MviAction> {
    suspend fun invokeActor(action: Action)
}
