package ru.braveowlet.kmmpr.common.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface Mvi<Action : MviAction, Event : MviEvent, State : MviState> {
    suspend fun acceptAction(action: Action)
    fun getState(scope: CoroutineScope): StateFlow<State>
    fun eventFlow(): SharedFlow<Event>
    fun logDebug(message: String)
}
