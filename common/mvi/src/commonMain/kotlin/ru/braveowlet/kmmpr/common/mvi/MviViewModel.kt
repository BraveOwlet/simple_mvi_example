package ru.braveowlet.kmmpr.common.mvi

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect abstract class MviViewModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    defaultState: State,
    actor: MviActor<Action, Effect, State>,
    boot: MviBoot<Effect>,
    eventProducer: MviEventProducer<Effect, Event, State>,
    stateProducer: MviStateProducer<Effect, State>,
    tag: String,
    logEnable: Boolean = true,
    logger: MviLogger = MviLoggerDefault,
){
    val mviController: MviController<Action, Effect, Event, State>
}