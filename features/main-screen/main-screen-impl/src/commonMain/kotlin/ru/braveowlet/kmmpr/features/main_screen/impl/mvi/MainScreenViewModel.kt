package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.di.MviViewModel

open class MainScreenViewModel : MviViewModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    defaultState = MainScreenState("", false),
    actor = MainScreenActor(),
    boot = MainScreenBoot(),
    eventProducer = MainScreenMviEventProducer(),
    stateProducer = MainScreenMviStateReducer(),
    tag = "FirstScreen",
    logEnable = true,
)