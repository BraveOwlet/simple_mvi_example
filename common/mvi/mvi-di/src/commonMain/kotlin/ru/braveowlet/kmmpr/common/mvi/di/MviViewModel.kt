package ru.braveowlet.kmmpr.common.mvi.di

import ru.braveowlet.kmmpr.common.logger.Logger
import ru.braveowlet.kmmpr.common.logger.LoggerDefault
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
