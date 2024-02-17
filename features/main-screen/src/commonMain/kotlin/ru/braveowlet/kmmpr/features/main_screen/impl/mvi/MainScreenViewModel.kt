package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.compose.MviScreenModel

class MainScreenModel :
    MviScreenModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
        defaultState = MainScreenState("", false),
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