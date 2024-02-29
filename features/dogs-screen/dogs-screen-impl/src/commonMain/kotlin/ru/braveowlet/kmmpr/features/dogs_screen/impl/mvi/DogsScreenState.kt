package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviState
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal data class DogsScreenState(
    val dog: Dog?,
): MviState {
    companion object{
        val default = DogsScreenState(null)
    }
}
