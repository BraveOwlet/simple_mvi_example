package ru.braveowlet.kmmpr.features.main_screen

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import ru.braveowlet.kmmpr.common.mvi.withMviViewModel
import ru.braveowlet.kmmpr.features.main_screen.compose.FirstScreenContent
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenAction
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenEffect
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenEvent
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenState
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenViewModel

@Composable
fun MainScreen() =
    withMviViewModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState, MainScreenViewModel> { state, eventFlow, acceptAction ->
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            eventFlow.collect { event ->
                when (event) {
                    is MainScreenEvent.ShowMessage -> snackbarHostState.showSnackbar(event.message)
                }
            }
        }

        FirstScreenContent(
            state = state,
            snackbarHostState = snackbarHostState,
            onClickButton = { acceptAction(MainScreenAction.ClickButton) }
        )
    }
