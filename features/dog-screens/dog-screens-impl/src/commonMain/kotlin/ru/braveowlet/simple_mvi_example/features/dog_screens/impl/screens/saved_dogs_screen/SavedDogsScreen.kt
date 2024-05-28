package ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.common.mvi.impl.MviView
import ru.braveowlet.common.mvi.impl.collectEvent
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.compose.SavedDogsScreenContent
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenAction
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenEvent
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenState

internal class SavedDogsScreen :
    MviView<SavedDogsScreenAction, SavedDogsScreenEvent, SavedDogsScreenState> {

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
