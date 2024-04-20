package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.intents

import ru.braveowlet.common.mvi.general.models.MviAction

internal sealed interface SavedDogsScreenAction : MviAction {
    data object ClickButtonBack : SavedDogsScreenAction
}
