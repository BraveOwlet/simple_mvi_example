package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal sealed interface DogsScreenEffect : MviEffect {
    data object ButtonBackClicked : DogsScreenEffect
    data class DogLoaded(val dog: Dog) : DogsScreenEffect
    data class DogLoadFiled(val throwable: Throwable) : DogsScreenEffect
}
