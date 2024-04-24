package ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen

import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.braveowlet.common.mvi.general.api.base.collectEvent
import ru.braveowlet.common.mvi.impl.MviScreen
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.compose.ResourcesScreenContent
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi.ResourcesScreenAction
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi.ResourcesScreenEffect
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi.ResourcesScreenEvent
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.mvi.ResourcesScreenState

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
            onClickButtonBack = { push(ResourcesScreenAction.ClickButtonBack) },
        )
    }
)
