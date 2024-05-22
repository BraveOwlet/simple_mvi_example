package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.common.mvi.general.Mvi
import ru.braveowlet.common.mvi.general.collectEvent
import ru.braveowlet.common.mvi.impl.MviView
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenAction
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenEvent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenState
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.compose.SavedDogsScreenContent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenAction
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenEffect
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenEvent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenState

internal class SavedDogsScreen: MviView<SavedDogsScreenAction, SavedDogsScreenEvent, SavedDogsScreenState> {

    @Composable
    override fun content(
        state: SavedDogsScreenState,
        eventFlow: Flow<SavedDogsScreenEvent>,
        pushAction: (SavedDogsScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow

        eventFlow.collectEvent { event ->
            when (event) {
                is SavedDogsScreenEvent.NavigateToBack -> navigator.pop()
            }
        }

        SavedDogsScreenContent(
            state = state,
            onClickBack = {
                pushAction(SavedDogsScreenAction.ClickButtonBack)
            }
        )
    }
}
