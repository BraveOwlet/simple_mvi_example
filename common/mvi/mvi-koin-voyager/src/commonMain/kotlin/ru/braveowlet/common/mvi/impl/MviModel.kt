package ru.braveowlet.common.mvi.impl

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import ru.braveowlet.common.mvi.general.Mvi
import ru.braveowlet.common.mvi.general.MviBaseModel
import ru.braveowlet.common.mvi.general.MviImpl
import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.common.mvi.general.MviEvent
import ru.braveowlet.common.mvi.general.MviState

abstract class MviModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    tag: String,
    logEnable: Boolean = true,
) : ScreenModel, MviBaseModel<Action, Effect, Event, State> {

    override val mvi: Mvi<Action, Effect, Event, State> by lazy {
        create(
            tag = tag,
            logEnable = logEnable,
            defaultState = defaultState,
            scope = screenModelScope,
            dispatcher = Dispatchers.Default,
        )
    }
}
