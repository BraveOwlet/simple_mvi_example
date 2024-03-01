package ru.braveowlet.kmmpr.features.resources_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.braveowlet.common.mvi.general.MviActor

internal class ResourcesScreenActor :
    MviActor<ResourcesScreenAction, ResourcesScreenEffect, ResourcesScreenState> {
    override suspend fun invoke(
        action: ResourcesScreenAction,
        state: ResourcesScreenState
    ): Flow<ResourcesScreenEffect> = flowOf(
        when (action) {
            ResourcesScreenAction.ClickButtonBack -> ResourcesScreenEffect.ButtonBackClicked
        }
    )
}
