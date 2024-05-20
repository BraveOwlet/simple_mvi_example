package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi

import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal sealed interface DogsScreenEffect : MviEffect {
    data class DogLoaded(val dog: Dog) : DogsScreenEffect
    data class DogLoadFiled(val throwable: Throwable) : DogsScreenEffect
}
