package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviState
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal data class SavedDogsScreenState(
    val dogs: List<Dog>,
) : MviState
