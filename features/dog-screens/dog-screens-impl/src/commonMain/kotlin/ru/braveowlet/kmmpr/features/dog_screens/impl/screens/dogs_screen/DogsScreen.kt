package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.braveowlet.common.mvi.general.collectEvent
import ru.braveowlet.common.mvi.impl.MviView
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.compose.DogsScreenContent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenAction
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenEffect
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenEvent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenState

internal class DogsScreen(
    tag: String
) : MviView<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
    tag = tag,
    content = { state ->
        val navigator = LocalNavigator.currentOrThrow
        val snackbarHostState = remember { SnackbarHostState() }

        collectEvent { event ->
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
                push(DogsScreenAction.ClickButtonBack)
            },
            onClickButtonGetDog = {
                push(DogsScreenAction.ClickButtonGetDog)
            },
            onClickButtonSaveDog = {
                push(DogsScreenAction.ClickButtonSaveDog(it))
            }
        )
    }
)
