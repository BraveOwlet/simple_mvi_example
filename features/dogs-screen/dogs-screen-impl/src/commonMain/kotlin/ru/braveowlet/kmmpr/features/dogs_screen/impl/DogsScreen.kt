package ru.braveowlet.kmmpr.features.dogs_screen.impl

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.common.mvi.koin.MviScreen
import ru.braveowlet.common.mvi.koin.collectEvent
import ru.braveowlet.kmmpr.features.dogs_screen.impl.compose.DogsScreenContent
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenAction
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenEffect
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenEvent
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenState

internal class DogsScreen(
    tag: String
) : MviScreen<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
    tag = tag
) {
    @Composable
    override fun MviContent(
        state: DogsScreenState,
        eventFlow: Flow<DogsScreenEvent>,
        acceptAction: (DogsScreenAction) -> Unit
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
            dog = state.dog,
            snackbarHostState = snackbarHostState,
            onClickButtonBack = {
                acceptAction(DogsScreenAction.ClickButtonBack)
            },
            onClickButtonGetDog = {
                acceptAction(DogsScreenAction.ClickButtonGetDog)
            },
            onClickButtonSaveDog = {
                acceptAction(DogsScreenAction.ClickButtonSaveDog(it))
            }
        )
    }
}
