package ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.model

import ru.braveowlet.common.mvi.general.models.MviActor
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.intents.MainScreenAction
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.intents.MainScreenEffect
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.intents.MainScreenEvent
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.model.state.MainScreenState

internal class MainScreenModel(
    tag: String
) : MviModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    defaultState = MainScreenState(""),
    tag = tag,
) {

    override val actor: MviActor<MainScreenAction> = MviActor { action ->
        when (action) {
            is MainScreenAction.ClickButtonDogsScreen ->
                controller.emit(MainScreenEvent.NavigateToDogsScreen)

            is MainScreenAction.ClickButtonSavedDogsScreen ->
                controller.emit(MainScreenEvent.NavigateToSavedDogsScreen)

            is MainScreenAction.ClickButtonResourcesScreen ->
                controller.emit(MainScreenEvent.NavigateToResourcesScreen)

            is MainScreenAction.ClickButtonFlowTestScreen ->
                controller.emit(MainScreenEvent.NavigateToFlowTestScreen)
        }
    }
}
