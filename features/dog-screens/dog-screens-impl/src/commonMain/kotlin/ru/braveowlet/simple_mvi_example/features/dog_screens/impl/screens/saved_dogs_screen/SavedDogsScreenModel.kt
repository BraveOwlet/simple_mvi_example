package ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase.ObserveRandomDogUseCase
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenAction
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenEffect
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenEvent
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenState

internal class SavedDogsScreenModel(
    tag: String,
    private val observeRandomDogUseCase: ObserveRandomDogUseCase,
) : MviModel<SavedDogsScreenAction, SavedDogsScreenEffect, SavedDogsScreenEvent, SavedDogsScreenState>(
    defaultState = SavedDogsScreenState.DEFAULT,
    tag = tag,
) {

    override suspend fun bootstrap() {
        observeRandomDogUseCase()
            .onEach { push(SavedDogsScreenEffect.DogsUpdated(it)) }
            .launchIn(screenModelScope)
    }

    override fun reducer(
        effect: SavedDogsScreenEffect,
        previousState: SavedDogsScreenState
    ): SavedDogsScreenState = when (effect) {
        is SavedDogsScreenEffect.DogsUpdated -> previousState.setDog(effect.dogs)
    }

    override suspend fun actor(action: SavedDogsScreenAction) = when (action) {
        SavedDogsScreenAction.ClickButtonBack ->
            push(SavedDogsScreenEvent.NavigateToBack)
    }
}
