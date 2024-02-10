package ru.braveowlet.kmmpr.common.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun <Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> getMvi(
    defaultState: State,
    actor: MviActor<Action, Effect, State>,
    boot: MviBoot<Effect>,
    eventProducer: MviEventProducer<Effect, Event, State>,
    stateProducer: MviStateProducer<Effect, State>,
    tag: String,
    logEnable: Boolean = true,
    logger: MviLogger = MviLoggerDefault,
): Mvi<Action, Event, State> {
    return remember {
        MviController(
            defaultState = defaultState,
            actor = actor,
            boot = boot,
            eventProducer = eventProducer,
            stateProducer = stateProducer,
            tag = tag,
            logEnable = logEnable,
            logger = logger,
        )
    }
}