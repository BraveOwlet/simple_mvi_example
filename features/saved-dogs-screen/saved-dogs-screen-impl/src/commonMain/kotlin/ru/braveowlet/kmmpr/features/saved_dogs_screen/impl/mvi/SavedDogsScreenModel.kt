package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.koin.MviScreenModel

internal class SavedDogsScreenModel :
    MviScreenModel<SavedDogsScreenAction, SavedDogsScreenEffect, SavedDogsScreenEvent, SavedDogsScreenState>(
        defaultState = SavedDogsScreenState(""),
        actor = SavedDogsScreenActor(),
        boot = SavedDogsScreenBoot(),
        eventProducer = SavedDogsScreenMviEventProducer(),
        stateReducer = SavedDogsScreenMviStateReducer(),
        tag = tag,
    ) {
    companion object {
        const val tag: String = "SavedDogsScreen"
    }
}