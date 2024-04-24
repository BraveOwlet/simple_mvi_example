package ru.braveowlet.common.mvi.general.api.base

import kotlinx.coroutines.CoroutineScope
import ru.braveowlet.common.mvi.general.api.MviInterractor
import ru.braveowlet.common.mvi.general.api.models.MviAction
import ru.braveowlet.common.mvi.general.api.models.MviActor
import ru.braveowlet.common.mvi.general.api.models.MviBootstrap
import ru.braveowlet.common.mvi.general.api.models.MviEffect
import ru.braveowlet.common.mvi.general.api.models.MviEvent
import ru.braveowlet.common.mvi.general.api.models.MviReducer
import ru.braveowlet.common.mvi.general.api.models.MviState

interface BaseMviModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> :
    MviReducer<Effect, State>, MviBootstrap, MviActor<Action> {

    val interractor: MviInterractor<Action, Effect, Event, State>
    override suspend fun invokeBootstrap(scope: CoroutineScope) = Unit
    override suspend fun invokeActor(action: Action, scope: CoroutineScope) = Unit
    override fun invokeReducer(effect: Effect, previousState: State): State = previousState
}
