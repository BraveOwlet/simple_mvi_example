package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviState

internal data class DogsScreenState(
    val urlImageDog: String,
): MviState{
    companion object{
        val default = DogsScreenState("")
    }
}
