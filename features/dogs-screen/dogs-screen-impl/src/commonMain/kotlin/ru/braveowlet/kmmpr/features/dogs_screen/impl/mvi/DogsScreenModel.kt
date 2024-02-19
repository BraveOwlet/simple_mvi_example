package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.koin.MviScreenModel

internal class DogsScreenModel :
    MviScreenModel<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
        defaultState = DogsScreenState(""),
        actor = DogsScreenActor(),
        boot = DogsScreenBoot(),
        eventProducer = DogsScreenMviEventProducer(),
        stateReducer = DogsScreenMviStateReducer(),
        tag = tag,
    ) {
    companion object {
        const val tag: String = "DogsScreen"
    }
}