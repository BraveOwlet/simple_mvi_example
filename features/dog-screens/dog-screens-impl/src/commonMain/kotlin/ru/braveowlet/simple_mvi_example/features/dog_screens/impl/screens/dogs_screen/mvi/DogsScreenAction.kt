package ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.mvi

import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.simple_mvi_example.components.dogs.domain.model.Dog

internal sealed interface DogsScreenAction : MviAction {
    data object ClickButtonBack : DogsScreenAction
    data object ClickButtonGetDog : DogsScreenAction
    data class ClickButtonSaveDog(val dog: Dog) : DogsScreenAction
}
