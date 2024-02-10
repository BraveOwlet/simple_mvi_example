package ru.braveowlet.kmmpr.features.first_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviController

class ScreenController : MviController<ScreenAction, ScreenEffect, ScreenEvent, ScreenState>(
    defaultState = ScreenState("", false),
    actor = ScreenActor(),
    boot = ScreenBoot(),
    eventProducer = ScreenMviEventProducer(),
    stateProducer = ScreenMviStateProducer(),
    tag = "screen",
    logEnable = false,
)