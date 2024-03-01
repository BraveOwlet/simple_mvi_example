package ru.braveowlet.kmmpr.features.resources_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviStateReducer

internal class ResourcesScreenMviStateReducer :
    MviStateReducer<ResourcesScreenEffect, ResourcesScreenState> {
    override suspend fun invoke(
        effect: ResourcesScreenEffect,
        previousState: ResourcesScreenState
    ): ResourcesScreenState = previousState
}
