package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.common.mvi.koin.MviScreenModel

internal class MainScreenModel :
    MviScreenModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
        defaultState = MainScreenState(""),
        actor = MainScreenActor(),
        boot = MainScreenBoot(),
        eventProducer = MainScreenMviEventProducer(),
        stateReducer = MainScreenMviStateReducer(),
        tag = tag,
    ) {
    companion object {
        const val tag: String = "MainScreen"
    }
}