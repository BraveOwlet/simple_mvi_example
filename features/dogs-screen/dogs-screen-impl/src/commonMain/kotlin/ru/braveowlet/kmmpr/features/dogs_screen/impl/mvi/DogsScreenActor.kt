package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.braveowlet.common.mvi.general.MviActor
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.GetRandomImageWithDogsUseCase
import ru.braveowlet.kmmpr.core.network.NetworkResult

internal class DogsScreenActor(
    private val getRandomImageWithDogsUseCase: GetRandomImageWithDogsUseCase,
) : MviActor<DogsScreenAction, DogsScreenEffect, DogsScreenState> {
    override suspend fun invoke(
        action: DogsScreenAction,
        state: DogsScreenState
    ): Flow<DogsScreenEffect> = flowOf(
        when (action) {
            is DogsScreenAction.ClickButtonBack ->
                DogsScreenEffect.ButtonBackClicked

            is DogsScreenAction.ClickButtonGetImageRandomDog ->
                processClickButtonGetImageRandomDog(getRandomImageWithDogsUseCase)
        }
    )
}

private suspend fun processClickButtonGetImageRandomDog(
    getRandomImageWithDogsUseCase: GetRandomImageWithDogsUseCase
): DogsScreenEffect = getRandomImageWithDogsUseCase().let {
    when (it) {
        is NetworkResult.Error ->
            DogsScreenEffect.ImageRandomDogLoadFiled(it.throwable)

        is NetworkResult.Success ->
            DogsScreenEffect.ImageRandomDogLoaded(it.value.url)
    }
}
