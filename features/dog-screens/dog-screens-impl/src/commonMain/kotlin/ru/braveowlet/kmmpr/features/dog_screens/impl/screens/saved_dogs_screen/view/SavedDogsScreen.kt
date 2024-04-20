package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.view

import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.braveowlet.common.mvi.general.presentation.collectEvent
import ru.braveowlet.common.mvi.impl.MviScreen
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.view.compose.SavedDogsScreenContent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.intents.SavedDogsScreenAction
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.intents.SavedDogsScreenEffect
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.intents.SavedDogsScreenEvent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.model.state.SavedDogsScreenState

internal class SavedDogsScreen(
    tag: String,
) : MviScreen<SavedDogsScreenAction, SavedDogsScreenEffect, SavedDogsScreenEvent, SavedDogsScreenState>(
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
                emit(SavedDogsScreenAction.ClickButtonBack)
            }
        )
    }
)
