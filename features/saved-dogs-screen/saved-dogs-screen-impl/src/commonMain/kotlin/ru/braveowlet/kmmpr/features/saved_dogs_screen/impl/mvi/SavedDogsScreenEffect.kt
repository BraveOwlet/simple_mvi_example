package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal sealed interface SavedDogsScreenEffect : MviEffect {
    data object ButtonBackClicked : SavedDogsScreenEffect
    data class DogsUpdated(val dogs: List<Dog>) : SavedDogsScreenEffect
}
