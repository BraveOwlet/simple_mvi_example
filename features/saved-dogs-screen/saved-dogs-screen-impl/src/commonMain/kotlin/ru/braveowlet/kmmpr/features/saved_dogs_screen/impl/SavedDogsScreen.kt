package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.common.mvi.koin.MviScreen
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.compose.SavedDogsScreenContent
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenAction
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenEffect
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenEvent
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenModel
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenState


internal class SavedDogsScreen :
    MviScreen<SavedDogsScreenAction, SavedDogsScreenEffect, SavedDogsScreenEvent, SavedDogsScreenState>(
        tag = SavedDogsScreenModel.tag
    ) {
    @Composable
    override fun MviContent(
        state: SavedDogsScreenState,
        eventFlow: Flow<SavedDogsScreenEvent>,
        acceptAction: (SavedDogsScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            eventFlow.collect { event ->
                when (event) {
                    is SavedDogsScreenEvent.NavigateToBack -> navigator.pop()
                }
            }
        }

        SavedDogsScreenContent(
            onClickBack = {
                acceptAction(SavedDogsScreenAction.ClickButtonBack)
            }
        )
    }

}