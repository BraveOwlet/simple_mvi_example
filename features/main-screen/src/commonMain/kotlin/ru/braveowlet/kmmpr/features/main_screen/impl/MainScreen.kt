package ru.braveowlet.kmmpr.features.main_screen.impl

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import ru.braveowlet.kmmpr.common.mvi.compose.MviScreen
import ru.braveowlet.kmmpr.features.main_screen.impl.compose.MainScreenContent
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenAction
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenEffect
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenEvent
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenModel
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenState

@Composable
fun MainScreen() =
    MviScreen<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState, MainScreenModel>(
        tag = MainScreenModel.tag,
    ) { state, eventFlow, acceptAction ->

        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            eventFlow.collect { event ->
                when (event) {
                    is MainScreenEvent.ShowMessage -> snackbarHostState.showSnackbar(event.message)
                }
            }
        }

        MainScreenContent(
            state = state,
            snackbarHostState = snackbarHostState,
            onClickButton = { acceptAction(MainScreenAction.ClickButton) }
        )
    }
