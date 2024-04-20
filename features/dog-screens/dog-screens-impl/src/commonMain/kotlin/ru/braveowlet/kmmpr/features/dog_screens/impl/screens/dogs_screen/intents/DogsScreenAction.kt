package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.intents

import ru.braveowlet.common.mvi.general.models.MviAction
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal sealed interface DogsScreenAction : MviAction {
    data object ClickButtonBack : DogsScreenAction
    data object ClickButtonGetDog : DogsScreenAction
    data class ClickButtonSaveDog(val dog: Dog) : DogsScreenAction
}
