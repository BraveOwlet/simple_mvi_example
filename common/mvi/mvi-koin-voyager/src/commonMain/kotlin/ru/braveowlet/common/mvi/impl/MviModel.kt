package ru.braveowlet.common.mvi.impl

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import ru.braveowlet.common.logger.DefaultLogger
import ru.braveowlet.common.logger.Logger
import ru.braveowlet.common.mvi.general.models.MviAction
import ru.braveowlet.common.mvi.general.models.MviActor
import ru.braveowlet.common.mvi.general.models.MviBootstrap
import ru.braveowlet.common.mvi.general.models.MviEffect
import ru.braveowlet.common.mvi.general.models.MviEvent
import ru.braveowlet.common.mvi.general.models.MviState
import ru.braveowlet.common.mvi.general.models.MviReducer
import ru.braveowlet.common.mvi.general.models.Mvi
import ru.braveowlet.common.mvi.general.MviController

abstract class MviModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    tag: String,
    logEnable: Boolean = true,
    logger: Logger = DefaultLogger,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ScreenModel {

    open val bootstrap: MviBootstrap = MviBootstrap()

    open val actor: MviActor<Action> = MviActor()

    open val reducer: MviReducer<Effect, State> = MviReducer()

    val controller: Mvi<Action, Effect, Event, State> by lazy {
        MviController(
            defaultState = defaultState,
            bootstrap = bootstrap,
            actor = actor,
            reducer = reducer,
            dispatcher = dispatcher,
            scope = screenModelScope,
            tag = tag,
            logEnable = logEnable,
            logger = logger,
        )
    }
}
