package ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents

import ru.braveowlet.common.mvi.general.models.MviAction

internal sealed interface ResourcesScreenAction : MviAction {
    data object ClickButtonBack : ResourcesScreenAction
}
