package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviEffect

internal sealed interface SavedDogsScreenEffect : MviEffect{
    data object ButtonBackClicked: SavedDogsScreenEffect
}