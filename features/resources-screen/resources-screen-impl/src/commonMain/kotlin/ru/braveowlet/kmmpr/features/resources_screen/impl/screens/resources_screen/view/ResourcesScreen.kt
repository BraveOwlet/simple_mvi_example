package ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.view

import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.braveowlet.common.mvi.general.presentation.collectEvent
import ru.braveowlet.common.mvi.impl.MviScreen
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.view.compose.ResourcesScreenContent
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents.ResourcesScreenAction
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents.ResourcesScreenEffect
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents.ResourcesScreenEvent
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.model.state.ResourcesScreenState

internal class ResourcesScreen(
    tag: String
) : MviScreen<ResourcesScreenAction, ResourcesScreenEffect, ResourcesScreenEvent, ResourcesScreenState>(
    tag = tag,
    content = { state ->
        val navigator = LocalNavigator.currentOrThrow

        collectEvent { event ->
            when (event) {
                ResourcesScreenEvent.NavigateToBack -> navigator.pop()
            }
        }

        ResourcesScreenContent(
            state = state,
            onClickButtonBack = { emit(ResourcesScreenAction.ClickButtonBack) },
        )
    }
)
