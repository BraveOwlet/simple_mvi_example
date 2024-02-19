package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import ru.braveowlet.kmmpr.common.mvi.general.MviActor

internal class DogsScreenActor : MviActor<DogsScreenAction, DogsScreenEffect, DogsScreenState> {
    override suspend fun invoke(
        action: DogsScreenAction,
        state: DogsScreenState
    ): Flow<DogsScreenEffect> = flowOf<DogsScreenEffect>(
        when(action){
            is DogsScreenAction.ClickButtonBack -> DogsScreenEffect.ButtonBackClicked
        }
    )
}
