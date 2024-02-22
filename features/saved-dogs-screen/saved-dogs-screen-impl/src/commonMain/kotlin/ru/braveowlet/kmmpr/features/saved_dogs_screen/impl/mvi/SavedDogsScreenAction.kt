package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviAction

internal sealed interface SavedDogsScreenAction : MviAction {
    data object ClickButtonBack : SavedDogsScreenAction
}