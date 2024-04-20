package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.model.state

import ru.braveowlet.common.mvi.general.models.MviState
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal data class DogsScreenState(
    val dog: Dog?,
) : MviState {
    companion object {
        val default = DogsScreenState(null)
    }

    fun clean(): DogsScreenState = copy(
        dog = null
    )

    fun setDog(dog: Dog) = copy(
        dog = dog
    )
}
