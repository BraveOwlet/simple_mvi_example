package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.common.mvi.koin.MviScreenModel

internal class MainScreenModel(
    tag: String
) : MviScreenModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    defaultState = MainScreenState(""),
    actor = MainScreenActor(),
    boot = MainScreenBoot(),
    eventProducer = MainScreenMviEventProducer(),
    stateReducer = MainScreenMviStateReducer(),
    tag = tag,
)