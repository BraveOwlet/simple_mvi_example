package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal sealed interface DogsScreenAction : MviAction {
    data object ClickButtonBack : DogsScreenAction
    data object ClickButtonGetDog : DogsScreenAction
    data class ClickButtonSaveDog(val dog: Dog) : DogsScreenAction
}
