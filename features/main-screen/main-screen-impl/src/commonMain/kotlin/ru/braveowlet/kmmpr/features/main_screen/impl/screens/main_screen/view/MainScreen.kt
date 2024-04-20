package ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.view

import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject
import ru.braveowlet.common.mvi.general.presentation.collectEvent
import ru.braveowlet.common.mvi.impl.MviScreen
import ru.braveowlet.kmmpr.features.dog_screens.api.DogScreensApi
import ru.braveowlet.kmmpr.features.flow_test_screen.api.FlowTestScreenApi
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.view.compose.MainScreenContent
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.intents.MainScreenAction
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.intents.MainScreenEffect
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.intents.MainScreenEvent
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.model.state.MainScreenState
import ru.braveowlet.kmmpr.features.resources_screen.api.ResourcesScreenApi

internal class MainScreen(
    tag: String
) : MviScreen<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    tag = tag,
    content = { state ->
        val navigator = LocalNavigator.currentOrThrow
        val dogScreensApi = koinInject<DogScreensApi>()
        val resourcesScreenApi = koinInject<ResourcesScreenApi>()
        val flowTestScreenApi = koinInject<FlowTestScreenApi>()

        collectEvent { event ->
            when (event) {
                is MainScreenEvent.NavigateToDogsScreen ->
                    navigator.push(dogScreensApi.dogsScreen())

                is MainScreenEvent.NavigateToSavedDogsScreen ->
                    navigator.push(dogScreensApi.savedDogsScreen())

                is MainScreenEvent.NavigateToResourcesScreen ->
                    navigator.push(resourcesScreenApi.resourcesScreen())

                is MainScreenEvent.NavigateToFlowTestScreen ->
                    navigator.push(flowTestScreenApi.flowTestScreen())
            }
        }

        MainScreenContent(
            state = state,
            onClickButtonDogsScreen = { emit(MainScreenAction.ClickButtonDogsScreen) },
            onClickButtonSavedDogsScreen = { emit(MainScreenAction.ClickButtonSavedDogsScreen) },
            onClickButtonResourcesScreen = { emit(MainScreenAction.ClickButtonResourcesScreen) },
            onClickButtonFlowTestScreen = { emit(MainScreenAction.ClickButtonFlowTestScreen) }
        )
    }
)
