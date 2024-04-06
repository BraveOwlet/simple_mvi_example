package ru.braveowlet.kmmpr.features.resources_screen.impl

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.common.mvi.koin.MviScreen
import ru.braveowlet.common.mvi.koin.collectEvent
import ru.braveowlet.kmmpr.features.resources_screen.impl.compose.ResourcesScreenContent
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenAction
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenEffect
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenEvent
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenState

internal class ResourcesScreen(
    tag: String
) : MviScreen<ResourcesScreenAction, ResourcesScreenEffect, ResourcesScreenEvent, ResourcesScreenState>(
    tag = tag
) {
    @Composable
    override fun MviContent(
        state: ResourcesScreenState,
        eventFlow: Flow<ResourcesScreenEvent>,
        acceptAction: (ResourcesScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow

        eventFlow.collectEvent { event ->
            when (event) {
                ResourcesScreenEvent.NavigateToBack -> navigator.pop()
            }
        }

        ResourcesScreenContent(
            state = state,
            onClickButtonBack = { acceptAction(ResourcesScreenAction.ClickButtonBack) },
        )
    }
}
