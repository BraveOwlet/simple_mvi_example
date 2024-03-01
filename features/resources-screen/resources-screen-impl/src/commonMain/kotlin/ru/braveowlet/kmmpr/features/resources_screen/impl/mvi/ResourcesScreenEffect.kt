package ru.braveowlet.kmmpr.features.resources_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviEffect

internal sealed interface ResourcesScreenEffect : MviEffect {
    data object ButtonBackClicked : ResourcesScreenEffect
}
