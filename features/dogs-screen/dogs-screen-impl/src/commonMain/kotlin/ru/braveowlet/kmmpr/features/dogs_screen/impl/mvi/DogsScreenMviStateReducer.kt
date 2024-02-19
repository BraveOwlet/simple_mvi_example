package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviStateReducer

internal class DogsScreenMviStateReducer : MviStateReducer<DogsScreenEffect, DogsScreenState> {
    override suspend fun invoke(
        effect: DogsScreenEffect,
        previousState: DogsScreenState
    ): DogsScreenState = previousState
}