package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.common.mvi.general.MviEventProducer

internal class MainScreenMviEventProducer :
    MviEventProducer<MainScreenEffect, MainScreenEvent, MainScreenState> {
    override suspend fun invoke(
        effect: MainScreenEffect,
        state: MainScreenState
    ): Flow<MainScreenEvent> = flow {
        when (effect) {
            is MainScreenEffect.ButtonDogsScreenClicked -> emit(MainScreenEvent.NavigateToDogsScreen)
            is MainScreenEffect.ButtonSavedDogsScreenClicked -> emit(MainScreenEvent.NavigateToSavedDogsScreen)
            is MainScreenEffect.ButtonResourcesScreenClicked -> emit(MainScreenEvent.NavigateToResourcesScreen)
        }
    }
}
