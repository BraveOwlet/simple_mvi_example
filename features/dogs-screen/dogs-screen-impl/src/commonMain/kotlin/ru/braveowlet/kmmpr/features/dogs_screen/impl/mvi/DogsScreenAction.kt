package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviAction

internal sealed interface DogsScreenAction : MviAction {
    data object ClickButtonBack : DogsScreenAction
    data object ClickButtonGetImageRandomDog : DogsScreenAction
}
