package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.koin.MviScreenModel

internal class DogsScreenModel(
    tag : String,
    dogsScreenActor: DogsScreenActor,
) : MviScreenModel<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
    defaultState = DogsScreenState.default,
    actor = dogsScreenActor,
    boot = DogsScreenBoot(),
    eventProducer = DogsScreenMviEventProducer(),
    stateReducer = DogsScreenMviStateReducer(),
    tag = tag,
)
