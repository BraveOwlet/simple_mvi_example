package ru.braveowlet.kmmpr.features.main_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviViewModel

class MainScreenViewModel : MviViewModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    defaultState = MainScreenState("", false),
    actor = MainScreenActor(),
    boot = MainScreenBoot(),
    eventProducer = MainScreenMviEventProducer(),
    stateProducer = MainScreenMviStateReducer(),
    tag = "FirstScreen",
    logEnable = true,
)