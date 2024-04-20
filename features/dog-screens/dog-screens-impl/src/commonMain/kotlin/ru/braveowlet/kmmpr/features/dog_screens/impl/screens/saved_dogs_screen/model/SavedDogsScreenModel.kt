package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.model

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.braveowlet.common.mvi.general.models.MviActor
import ru.braveowlet.common.mvi.general.models.MviBootstrap
import ru.braveowlet.common.mvi.general.models.MviReducer
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.ObserveRandomDogUseCase
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.intents.SavedDogsScreenAction
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.intents.SavedDogsScreenEffect
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.intents.SavedDogsScreenEvent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.model.state.SavedDogsScreenState

internal class SavedDogsScreenModel(
    tag: String,
    private val observeRandomDogUseCase: ObserveRandomDogUseCase,
) : MviModel<SavedDogsScreenAction, SavedDogsScreenEffect, SavedDogsScreenEvent, SavedDogsScreenState>(
    defaultState = SavedDogsScreenState(emptyList()),
    tag = tag,
) {

    override val reducer: MviReducer<SavedDogsScreenEffect, SavedDogsScreenState> =
        MviReducer { effect, previousState ->
            when (effect) {
                is SavedDogsScreenEffect.DogsUpdated ->
                    previousState.setDog(effect.dogs)
            }
        }

    override val bootstrap: MviBootstrap = MviBootstrap {
        observeRandomDogUseCase()
            .onEach { controller.emit(SavedDogsScreenEffect.DogsUpdated(it)) }
            .launchIn(this)
    }

    override val actor: MviActor<SavedDogsScreenAction> = MviActor { action ->
        when (action) {
            SavedDogsScreenAction.ClickButtonBack ->
                controller.emit(SavedDogsScreenEvent.NavigateToBack)
        }
    }
}
