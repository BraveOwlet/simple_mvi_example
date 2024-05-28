package ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.mvi

import ru.braveowlet.common.mvi.general.MviAction

internal sealed interface SavedDogsScreenAction : MviAction {
    data object ClickButtonBack : SavedDogsScreenAction
}
