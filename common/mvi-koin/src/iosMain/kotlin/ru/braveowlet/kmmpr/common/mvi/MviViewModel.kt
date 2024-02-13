package ru.braveowlet.kmmpr.common.mvi

import ru.braveowlet.kmmpr.common.logger.Logger

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
){
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
