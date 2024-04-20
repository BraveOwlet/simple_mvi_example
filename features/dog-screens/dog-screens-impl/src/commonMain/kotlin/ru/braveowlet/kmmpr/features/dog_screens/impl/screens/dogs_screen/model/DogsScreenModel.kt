package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.model

import ru.braveowlet.common.mvi.general.models.MviActor
import ru.braveowlet.common.mvi.general.models.MviReducer
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.GetRandomDogUseCase
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.SaveDogUseCase
import ru.braveowlet.kmmpr.core.network.handleMap
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.intents.DogsScreenAction
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.intents.DogsScreenEffect
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.intents.DogsScreenEvent
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.model.state.DogsScreenState

internal class DogsScreenModel(
    tag: String,
    private val getRandomDogUseCase: GetRandomDogUseCase,
    private val saveDogUseCase: SaveDogUseCase,
) : MviModel<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
    defaultState = DogsScreenState.default,
    tag = tag,
) {

    override val reducer: MviReducer<DogsScreenEffect, DogsScreenState> =
        MviReducer { effect, previousState ->
            when (effect) {
                is DogsScreenEffect.DogLoadFiled -> previousState.clean()
                is DogsScreenEffect.DogLoaded -> previousState.setDog(effect.dog)
            }
        }


    override val actor: MviActor<DogsScreenAction> = MviActor { action ->
        when (action) {
            is DogsScreenAction.ClickButtonBack ->
                controller.emit(DogsScreenEvent.NavigateToBack)

            is DogsScreenAction.ClickButtonGetDog ->
                controller.emit(processClickButtonGetDog(getRandomDogUseCase))

            is DogsScreenAction.ClickButtonSaveDog ->
                saveDogUseCase(action.dog)
        }
    }

    private suspend fun processClickButtonGetDog(
        getRandomDogUseCase: GetRandomDogUseCase,
    ): DogsScreenEffect = getRandomDogUseCase().handleMap(
        onSuccess = { DogsScreenEffect.DogLoaded(it) },
        onError = { DogsScreenEffect.DogLoadFiled(it) }
    )
}
