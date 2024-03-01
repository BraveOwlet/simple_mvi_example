package ru.braveowlet.kmmpr.features.resources_screen.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.common.logger.DefaultLogger
import ru.braveowlet.common.mvi.koin.MviScreen
import ru.braveowlet.kmmpr.features.resources_screen.impl.compose.ResourcesScreenContent
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenAction
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenEffect
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenEvent
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenModel
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenState

internal class ResourcesScreen :
    MviScreen<ResourcesScreenAction, ResourcesScreenEffect, ResourcesScreenEvent, ResourcesScreenState>(
        tag = ResourcesScreenModel.tag
    ) {
    @Composable
    override fun MviContent(
        state: ResourcesScreenState,
        eventFlow: Flow<ResourcesScreenEvent>,
        acceptAction: (ResourcesScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            eventFlow.collect { event ->
                when (event) {
                    ResourcesScreenEvent.NavigateToBack -> navigator.pop()
                }
            }
        }

        ResourcesScreenContent(
            state = state,
            onClickButtonBack = { acceptAction(ResourcesScreenAction.ClickButtonBack) },
        )
    }
}
