package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.common.mvi.general.MviEffect

internal sealed interface MainScreenEffect : MviEffect {
    data object ButtonDogsScreenClicked : MainScreenEffect
    data object ButtonSavedDogsScreenClicked : MainScreenEffect
}