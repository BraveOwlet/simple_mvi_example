package ru.braveowlet.kmmpr.common.mvi.koin

import cafe.adriel.voyager.core.model.ScreenModel
import ru.braveowlet.kmmpr.common.logger.DefaultLogger
import ru.braveowlet.kmmpr.common.logger.Logger
import ru.braveowlet.kmmpr.common.mvi.general.MviAction
import ru.braveowlet.kmmpr.common.mvi.general.MviActor
import ru.braveowlet.kmmpr.common.mvi.general.MviBoot
import ru.braveowlet.kmmpr.common.mvi.general.MviController
import ru.braveowlet.kmmpr.common.mvi.general.MviEffect
import ru.braveowlet.kmmpr.common.mvi.general.MviEvent
import ru.braveowlet.kmmpr.common.mvi.general.MviEventProducer
import ru.braveowlet.kmmpr.common.mvi.general.MviState
import ru.braveowlet.kmmpr.common.mvi.general.MviStateReducer

abstract class MviScreenModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    actor: MviActor<Action, Effect, State>,
    boot: MviBoot<Effect>,
    eventProducer: MviEventProducer<Effect, Event, State>,
    stateReducer: MviStateReducer<Effect, State>,
    tag: String,
    logEnable: Boolean = true,
    logger: Logger = DefaultLogger,
) : ScreenModel, MviController<Action, Effect, Event, State>(
    defaultState = defaultState,
    actor = actor,
    boot = boot,
    eventProducer = eventProducer,
    stateReducer = stateReducer,
    tag = tag,
    logEnable = logEnable,
    logger = logger,
)
