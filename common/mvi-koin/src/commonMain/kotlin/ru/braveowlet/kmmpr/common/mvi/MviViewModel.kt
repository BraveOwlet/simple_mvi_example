package ru.braveowlet.kmmpr.common.mvi

import ru.braveowlet.kmmpr.common.logger.Logger
import ru.braveowlet.kmmpr.common.logger.LoggerDefault

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect open class MviViewModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    actor: MviActor<Action, Effect, State>,
    boot: MviBoot<Effect>,
    eventProducer: MviEventProducer<Effect, Event, State>,
    stateProducer: MviStateReducer<Effect, State>,
    tag: String,
    logEnable: Boolean = true,
    logger: Logger = LoggerDefault,
){
    val mviController: MviController<Action, Effect, Event, State>
}
