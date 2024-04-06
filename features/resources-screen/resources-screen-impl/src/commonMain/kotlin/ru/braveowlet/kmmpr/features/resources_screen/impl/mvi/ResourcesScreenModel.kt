package ru.braveowlet.kmmpr.features.resources_screen.impl.mvi

import ru.braveowlet.common.mvi.koin.MviScreenModel

internal class ResourcesScreenModel :
    MviScreenModel<ResourcesScreenAction, ResourcesScreenEffect, ResourcesScreenEvent, ResourcesScreenState>(
        defaultState = ResourcesScreenState(""),
        actor = ResourcesScreenActor(),
        boot = ResourcesScreenBoot(),
        eventProducer = ResourcesScreenMviEventProducer(),
        stateReducer = ResourcesScreenMviStateReducer(),
        tag = tag,
    ) {
    companion object {
        const val tag: String = "ResourcesScreen"
    }
}