package ru.braveowlet.kmmpr.features.resources_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.common.mvi.general.MviEventProducer

internal class ResourcesScreenMviEventProducer :
    MviEventProducer<ResourcesScreenEffect, ResourcesScreenEvent, ResourcesScreenState> {
    override suspend fun invoke(
        effect: ResourcesScreenEffect,
        state: ResourcesScreenState
    ): Flow<ResourcesScreenEvent> = flow {
        when (effect) {
            ResourcesScreenEffect.ButtonBackClicked -> emit(ResourcesScreenEvent.NavigateToBack)
        }
    }
}
