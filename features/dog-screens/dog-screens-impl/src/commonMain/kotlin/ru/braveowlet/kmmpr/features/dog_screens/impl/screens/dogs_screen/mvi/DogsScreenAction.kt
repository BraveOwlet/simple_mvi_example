package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi

import ru.braveowlet.common.mvi.general.api.models.MviAction
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal sealed interface DogsScreenAction : MviAction {
    data object ClickButtonBack : DogsScreenAction
    data object ClickButtonGetDog : DogsScreenAction
    data class ClickButtonSaveDog(val dog: Dog) : DogsScreenAction
}
