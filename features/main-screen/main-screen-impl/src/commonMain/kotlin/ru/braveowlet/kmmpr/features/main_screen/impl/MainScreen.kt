package ru.braveowlet.kmmpr.features.main_screen.impl

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import ru.braveowlet.kmmpr.common.mvi.di.mviScreen
import ru.braveowlet.kmmpr.features.main_screen.impl.compose.FirstScreenContent
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenAction
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenEffect
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenEvent
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenState
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenViewModel

@Composable
fun MainScreen() =
    mviScreen<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState, MainScreenViewModel> { state, eventFlow, acceptAction ->
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
