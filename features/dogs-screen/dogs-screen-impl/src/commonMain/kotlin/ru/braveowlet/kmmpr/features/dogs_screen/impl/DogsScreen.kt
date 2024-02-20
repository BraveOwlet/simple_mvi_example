package ru.braveowlet.kmmpr.features.dogs_screen.impl

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.common.mvi.koin.MviScreen
import ru.braveowlet.kmmpr.features.dogs_screen.impl.compose.DogsScreenContent
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenAction
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenEffect
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenEvent
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenModel
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenState

internal class DogsScreen :
    MviScreen<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
        tag = DogsScreenModel.tag
    ) {
    @Composable
    override fun MviContent(
        state: DogsScreenState,
        eventFlow: Flow<DogsScreenEvent>,
        acceptAction: (DogsScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            eventFlow.collect { event ->
                when (event) {
                    is DogsScreenEvent.NavigateToBack ->
                        navigator.pop()

                    is DogsScreenEvent.ShowError -> snackbarHostState.showSnackbar(
                        message = event.message ?: ""
                    )
                }
            }
        }

        DogsScreenContent(
            urlImageDog = state.urlImageDog,
            snackbarHostState = snackbarHostState,
            onClickButtonBack = {
                acceptAction(DogsScreenAction.ClickButtonBack)
            },
            onClickButtonGetImageRandomDog = {
                acceptAction(DogsScreenAction.ClickButtonGetImageRandomDog)
            }
        )
    }
}
