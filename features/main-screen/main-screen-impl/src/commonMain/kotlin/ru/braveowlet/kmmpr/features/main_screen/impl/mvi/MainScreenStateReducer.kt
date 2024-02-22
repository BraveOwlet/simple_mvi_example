package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviStateReducer

internal class MainScreenMviStateReducer : MviStateReducer<MainScreenEffect, MainScreenState> {
    override suspend fun invoke(
        effect: MainScreenEffect,
        previousState: MainScreenState
    ): MainScreenState = previousState
}