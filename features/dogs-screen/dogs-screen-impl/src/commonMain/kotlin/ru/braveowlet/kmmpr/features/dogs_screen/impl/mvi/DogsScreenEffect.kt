package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviEffect

internal sealed interface DogsScreenEffect : MviEffect {
    data object ButtonBackClicked : DogsScreenEffect
    data class ImageRandomDogLoaded(val url: String) : DogsScreenEffect
    data class ImageRandomDogLoadFiled(val throwable: Throwable) : DogsScreenEffect
}
