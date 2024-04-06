package ru.braveowlet.kmmpr.features.resources_screen.impl.mvi

import ru.braveowlet.common.mvi.koin.MviScreenModel

internal class ResourcesScreenModel(
    tag: String
) : MviScreenModel<ResourcesScreenAction, ResourcesScreenEffect, ResourcesScreenEvent, ResourcesScreenState>(
    defaultState = ResourcesScreenState(""),
    actor = ResourcesScreenActor(),
    boot = ResourcesScreenBoot(),
    eventProducer = ResourcesScreenMviEventProducer(),
    stateReducer = ResourcesScreenMviStateReducer(),
    tag = tag,
)
