package ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen

import kotlinx.coroutines.CoroutineScope
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi.ResourcesScreenAction
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi.ResourcesScreenEffect
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi.ResourcesScreenEvent
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi.ResourcesScreenState

internal class ResourcesScreenModel(
    tag: String
) : MviModel<ResourcesScreenAction, ResourcesScreenEffect, ResourcesScreenEvent, ResourcesScreenState>(
    defaultState = ResourcesScreenState,
    tag = tag,
) {

    override suspend fun invokeActor(action: ResourcesScreenAction, scope: CoroutineScope) =
        when (action) {
            ResourcesScreenAction.ClickButtonBack ->
                interractor.push(ResourcesScreenEvent.NavigateToBack)
        }
}
