package ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.model

import ru.braveowlet.common.mvi.general.models.MviActor
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents.ResourcesScreenAction
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents.ResourcesScreenEffect
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents.ResourcesScreenEvent
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents.ResourcesScreenState

internal class ResourcesScreenModel(
    tag: String
) : MviModel<ResourcesScreenAction, ResourcesScreenEffect, ResourcesScreenEvent, ResourcesScreenState>(
    defaultState = ResourcesScreenState(""),
    tag = tag,
) {

    override val actor: MviActor<ResourcesScreenAction> = MviActor { action ->
        when (action) {
            ResourcesScreenAction.ClickButtonBack ->
                controller.emit(ResourcesScreenEvent.NavigateToBack)
        }
    }
}
