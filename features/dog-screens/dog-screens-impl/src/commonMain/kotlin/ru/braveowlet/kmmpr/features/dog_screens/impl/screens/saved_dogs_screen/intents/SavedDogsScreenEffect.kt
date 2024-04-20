package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.intents

import ru.braveowlet.common.mvi.general.models.MviEffect
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal sealed interface SavedDogsScreenEffect : MviEffect {
    data class DogsUpdated(val dogs: List<Dog>) : SavedDogsScreenEffect
}
