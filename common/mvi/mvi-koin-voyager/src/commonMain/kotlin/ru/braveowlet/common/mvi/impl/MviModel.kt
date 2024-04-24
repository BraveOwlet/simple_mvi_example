package ru.braveowlet.common.mvi.impl

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import ru.braveowlet.common.mvi.general.api.models.MviAction
import ru.braveowlet.common.mvi.general.api.models.MviEffect
import ru.braveowlet.common.mvi.general.api.models.MviEvent
import ru.braveowlet.common.mvi.general.api.models.MviState
import ru.braveowlet.common.mvi.general.api.models.MviCoroutineScope
import ru.braveowlet.common.mvi.general.api.MviInterractor
import ru.braveowlet.common.mvi.general.api.base.BaseMviModel

abstract class MviModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    tag: String,
    logEnable: Boolean = true,
    customLogEnable: Boolean = true,
) : ScreenModel, BaseMviModel<Action, Effect, Event, State> {

    override val interractor: MviInterractor<Action, Effect, Event, State> by lazy {
        MviInterractor.Builder<Action, Effect, Event, State>()
            .setTag(tag)
            .setLogEnable(logEnable)
            .setCustomLogEnable(customLogEnable)
            .setDefaultState(defaultState)
            .setScope(MviCoroutineScope(screenModelScope, Dispatchers.Default))
            .setActor(this)
            .setBootstrap(this)
            .setReducer(this)
            .build()
    }
}
