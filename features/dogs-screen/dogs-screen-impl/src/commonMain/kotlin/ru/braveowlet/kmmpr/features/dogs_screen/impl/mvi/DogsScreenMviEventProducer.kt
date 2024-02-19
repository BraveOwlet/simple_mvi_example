package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.kmmpr.common.mvi.general.MviEventProducer

internal class DogsScreenMviEventProducer :
    MviEventProducer<DogsScreenEffect, DogsScreenEvent, DogsScreenState> {
    override suspend fun invoke(
        effect: DogsScreenEffect,
        state: DogsScreenState
    ): Flow<DogsScreenEvent> = flow {
        when (effect) {
            is DogsScreenEffect.ButtonBackClicked -> emit(DogsScreenEvent.NavigateToBack)
        }
    }
}