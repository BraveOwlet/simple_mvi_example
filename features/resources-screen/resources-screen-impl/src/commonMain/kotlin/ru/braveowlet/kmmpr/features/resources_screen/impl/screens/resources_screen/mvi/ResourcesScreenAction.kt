package ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi

import ru.braveowlet.common.mvi.general.api.models.MviAction

internal sealed interface ResourcesScreenAction : MviAction {
    data object ClickButtonBack : ResourcesScreenAction
}
