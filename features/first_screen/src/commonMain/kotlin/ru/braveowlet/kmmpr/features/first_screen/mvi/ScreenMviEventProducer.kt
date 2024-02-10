package ru.braveowlet.kmmpr.features.first_screen.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.kmmpr.common.mvi.MviEventProducer

class ScreenMviEventProducer : MviEventProducer<ScreenEffect, ScreenEvent, ScreenState> {
    override suspend fun invoke(effect: ScreenEffect, state: ScreenState): Flow<ScreenEvent> =
        flow {
            when (effect) {
                is ScreenEffect.ButtonClicked -> emit(ScreenEvent.ShowMessage("ButtonClicked"))
                else -> {}
            }
        }
}