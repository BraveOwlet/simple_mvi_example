package ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.common.mvi.impl.MviView
import ru.braveowlet.common.mvi.impl.collectEvent
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.compose.DogsScreenContent
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenAction
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenEvent
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenState

internal class DogsScreen : MviView<DogsScreenAction, DogsScreenEvent, DogsScreenState> {

    @Composable
    override fun content(
        state: DogsScreenState,
        eventFlow: Flow<DogsScreenEvent>,
        pushAction: (DogsScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val snackbarHostState = remember { SnackbarHostState() }

        eventFlow.collectEvent { event ->
            when (event) {
                is DogsScreenEvent.NavigateToBack ->
                    navigator.pop()

                is DogsScreenEvent.ShowError -> snackbarHostState.showSnackbar(
                    message = event.message ?: ""
                )
            }
        }

        DogsScreenContent(
            state = state,
            snackbarHostState = snackbarHostState,
            onClickButtonBack = {
                pushAction(DogsScreenAction.ClickButtonBack)
            },
            onClickButtonGetDog = {
                pushAction(DogsScreenAction.ClickButtonGetDog)
            },
            onClickButtonSaveDog = {
                pushAction(DogsScreenAction.ClickButtonSaveDog(it))
            }
        )
    }
}
