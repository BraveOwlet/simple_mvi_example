package ru.braveowlet.common.mvi.general.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Mvi
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> {
    val state : StateFlow<State>
    val events : Flow<Event>
    fun emit(action: Action)
    fun emit(effect: Effect)
    fun emit(event: Event)
    fun log(message: String, isDebug : Boolean = false)
}
