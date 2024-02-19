package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviEffect

internal sealed interface DogsScreenEffect : MviEffect{
    data object ButtonBackClicked: DogsScreenEffect
}