package ru.braveowlet.kmmpr.features.first_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviStateProducer

class ScreenMviStateProducer : MviStateProducer<ScreenEffect, ScreenState> {
    override suspend fun invoke(effect: ScreenEffect, previousState: ScreenState): ScreenState =
        when (effect) {
            is ScreenEffect.BootEffect -> previousState.copy(
                data = effect.data
            )

            is ScreenEffect.ButtonClicked -> previousState.copy(
                logoIsShowed = !previousState.logoIsShowed
            )
        }
}