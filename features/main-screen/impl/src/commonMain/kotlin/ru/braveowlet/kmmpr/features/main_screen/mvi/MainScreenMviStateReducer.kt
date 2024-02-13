package ru.braveowlet.kmmpr.features.main_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviStateReducer

class MainScreenMviStateReducer : MviStateReducer<MainScreenEffect, MainScreenState> {
    override suspend fun invoke(effect: MainScreenEffect, previousState: MainScreenState): MainScreenState =
        when (effect) {
            is MainScreenEffect.BootEffectMain -> previousState.copy(
                data = effect.data
            )

            is MainScreenEffect.ButtonClicked -> previousState.copy(
                logoIsShowed = !previousState.logoIsShowed
            )
        }
}