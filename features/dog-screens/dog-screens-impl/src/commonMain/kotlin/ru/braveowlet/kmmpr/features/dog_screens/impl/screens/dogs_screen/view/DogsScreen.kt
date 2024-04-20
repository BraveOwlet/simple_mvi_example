package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.view

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.braveowlet.common.mvi.general.presentation.collectEvent
import ru.braveowlet.common.mvi.impl.MviScreen
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.view.compose.DogsScreenContent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.intents.DogsScreenAction
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.intents.DogsScreenEffect
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.intents.DogsScreenEvent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.intents.DogsScreenState

internal class DogsScreen(
    tag: String
) : MviScreen<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
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
            dog = state.dog,
            snackbarHostState = snackbarHostState,
            onClickButtonBack = {
                emit(DogsScreenAction.ClickButtonBack)
            },
            onClickButtonGetDog = {
                emit(DogsScreenAction.ClickButtonGetDog)
            },
            onClickButtonSaveDog = {
                emit(DogsScreenAction.ClickButtonSaveDog(it))
            }
        )
    }
)
