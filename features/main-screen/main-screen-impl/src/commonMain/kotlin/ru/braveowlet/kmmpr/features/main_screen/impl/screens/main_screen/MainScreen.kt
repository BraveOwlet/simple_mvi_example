package ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import org.koin.compose.koinInject
import ru.braveowlet.common.mvi.impl.MviView
import ru.braveowlet.common.mvi.impl.collectEvent
import ru.braveowlet.kmmpr.features.dog_screens.api.DogScreensApi
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.compose.MainScreenContent
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenAction
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenEvent
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.mvi.MainScreenState

internal class MainScreen : MviView<MainScreenAction, MainScreenEvent, MainScreenState> {

    @Composable
    override fun content(
        state: MainScreenState,
        eventFlow: Flow<MainScreenEvent>,
        pushAction: (MainScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val dogScreensApi = koinInject<DogScreensApi>()

        eventFlow.collectEvent { event ->
            when (event) {
                is MainScreenEvent.NavigateToDogsScreen ->
                    navigator.push(dogScreensApi.dogsScreen())

                is MainScreenEvent.NavigateToSavedDogsScreen ->
                    navigator.push(dogScreensApi.savedDogsScreen())
            }
        }

        MainScreenContent(
            state = state,
            onClickButtonDogsScreen = {
                pushAction(MainScreenAction.ClickButtonDogsScreen)
            },
            onClickButtonSavedDogsScreen = {
                pushAction(MainScreenAction.ClickButtonSavedDogsScreen)
            },
        )
    }
}
