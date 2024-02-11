package ru.braveowlet.kmmpr.features.first_screen

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import ru.braveowlet.kmmpr.common.mvi.withMviViewModel
import ru.braveowlet.kmmpr.features.first_screen.compose.FirstScreenContent
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenAction
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenEffect
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenEvent
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenState
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenViewModel

@Composable
fun FirstScreen() =
    withMviViewModel<ScreenAction, ScreenEffect, ScreenEvent, ScreenState, ScreenViewModel> { state, eventFlow, acceptAction ->
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            eventFlow.collect { event ->
                when (event) {
                    is ScreenEvent.ShowMessage -> snackbarHostState.showSnackbar(event.message)
                }
            }
        }

        FirstScreenContent(
            state = state,
            snackbarHostState = snackbarHostState,
            onClickButton = { acceptAction(ScreenAction.ClickButton) }
        )
    }
