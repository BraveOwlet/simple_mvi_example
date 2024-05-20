package ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen

import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject
import ru.braveowlet.common.mvi.general.collectEvent
import ru.braveowlet.common.mvi.impl.MviView
import ru.braveowlet.kmmpr.features.dog_screens.api.DogScreensApi
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.compose.MainScreenContent
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenAction
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenEffect
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenEvent
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenState

internal class MainScreen(
    tag: String
) : MviView<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    tag = tag,
    content = { state ->
        val navigator = LocalNavigator.currentOrThrow
        val dogScreensApi = koinInject<DogScreensApi>()

        collectEvent { event ->
            when (event) {
                is MainScreenEvent.NavigateToDogsScreen ->
                    navigator.push(dogScreensApi.dogsScreen())

                is MainScreenEvent.NavigateToSavedDogsScreen ->
                    navigator.push(dogScreensApi.savedDogsScreen())
            }
        }

        MainScreenContent(
            state = state,
            onClickButtonDogsScreen = { push(MainScreenAction.ClickButtonDogsScreen) },
            onClickButtonSavedDogsScreen = { push(MainScreenAction.ClickButtonSavedDogsScreen) },
        )
    }
)
