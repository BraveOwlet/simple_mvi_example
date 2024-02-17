package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.kmmpr.common.mvi.general.MviEventProducer

class MainScreenMviEventProducer :
    MviEventProducer<MainScreenEffect, MainScreenEvent, MainScreenState> {
    override suspend fun invoke(effect: MainScreenEffect, state: MainScreenState): Flow<MainScreenEvent> =
        flow {
            when (effect) {
                is MainScreenEffect.ButtonClicked -> emit(MainScreenEvent.ShowMessage("ButtonClicked"))
                else -> {}
            }
        }
}