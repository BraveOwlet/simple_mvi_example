package ru.braveowlet.common.mvi.general.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.braveowlet.common.mvi.general.api.models.MviAction
import ru.braveowlet.common.mvi.general.api.models.MviEffect
import ru.braveowlet.common.mvi.general.api.models.MviEvent
import ru.braveowlet.common.mvi.general.api.models.MviState

interface Mvi
<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> {
    val state: StateFlow<State>
    val events: Flow<Event>
    fun push(action: Action)
    fun log(message: () -> String)
}
