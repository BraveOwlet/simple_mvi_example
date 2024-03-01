package ru.braveowlet.kmmpr.features.resources_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviAction

internal sealed interface ResourcesScreenAction : MviAction {
    data object ClickButtonBack : ResourcesScreenAction
}
