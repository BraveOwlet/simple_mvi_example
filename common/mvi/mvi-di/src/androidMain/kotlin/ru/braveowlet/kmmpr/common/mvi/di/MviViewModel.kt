package ru.braveowlet.kmmpr.common.mvi.di

import androidx.lifecycle.ViewModel
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

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class MviViewModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> actual constructor(
    defaultState: State,
    actor: MviActor<Action, Effect, State>,
    boot: MviBoot<Effect>,
    eventProducer: MviEventProducer<Effect, Event, State>,
    stateProducer: MviStateReducer<Effect, State>,
    tag: String,
    logEnable: Boolean,
    logger: Logger,
) : ViewModel() {
    actual val mviController: MviController<Action, Effect, Event, State> = MviController(
        defaultState = defaultState,
        actor = actor,
        boot = boot,
        eventProducer = eventProducer,
        stateReducer = stateProducer,
        tag = tag,
        logEnable = logEnable,
        logger = logger
    )
}
