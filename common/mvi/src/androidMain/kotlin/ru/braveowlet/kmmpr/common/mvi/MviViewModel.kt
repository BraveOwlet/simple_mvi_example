package ru.braveowlet.kmmpr.common.mvi

import androidx.lifecycle.ViewModel

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual abstract class MviViewModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> actual constructor(
    defaultState: State,
    actor: MviActor<Action, Effect, State>,
    boot: MviBoot<Effect>,
    eventProducer: MviEventProducer<Effect, Event, State>,
    stateProducer: MviStateProducer<Effect, State>,
    tag: String,
    logEnable: Boolean,
    logger: MviLogger,
) : ViewModel() {
    actual val mviController: MviController<Action, Effect, Event, State> = MviController(
        defaultState = defaultState,
        actor = actor,
        boot = boot,
        eventProducer = eventProducer,
        stateProducer = stateProducer,
        tag = tag,
        logEnable = logEnable,
        logger = logger
    )
}