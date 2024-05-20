package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen

import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.braveowlet.common.mvi.general.collectEvent
import ru.braveowlet.common.mvi.impl.MviView
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.compose.SavedDogsScreenContent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenAction
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenEffect
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenEvent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenState

internal class SavedDogsScreen(
    tag: String,
) : MviView<SavedDogsScreenAction, SavedDogsScreenEffect, SavedDogsScreenEvent, SavedDogsScreenState>(
    tag = tag,
    content = { state ->
        val navigator = LocalNavigator.currentOrThrow

        collectEvent { event ->
            when (event) {
                is SavedDogsScreenEvent.NavigateToBack -> navigator.pop()
            }
        }

        SavedDogsScreenContent(
            state = state,
            onClickBack = {
                push(SavedDogsScreenAction.ClickButtonBack)
            }
        )
    }
)
