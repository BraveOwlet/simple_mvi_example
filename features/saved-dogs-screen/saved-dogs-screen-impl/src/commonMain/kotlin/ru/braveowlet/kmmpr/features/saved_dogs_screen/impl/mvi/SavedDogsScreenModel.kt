package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.koin.MviScreenModel

internal class SavedDogsScreenModel(
    boot: SavedDogsScreenBoot
) : MviScreenModel<SavedDogsScreenAction, SavedDogsScreenEffect, SavedDogsScreenEvent, SavedDogsScreenState>(
    defaultState = SavedDogsScreenState(emptyList()),
    actor = SavedDogsScreenActor(),
    boot = boot,
    eventProducer = SavedDogsScreenMviEventProducer(),
    stateReducer = SavedDogsScreenMviStateReducer(),
    tag = tag,
) {
    companion object {
        const val tag: String = "SavedDogsScreen"
    }
}
