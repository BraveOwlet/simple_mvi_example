package ru.braveowlet.kmmpr.features.first_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviEffect

sealed interface ScreenEffect : MviEffect {
    data class BootEffect(val data: String) : ScreenEffect
    data object ButtonClicked : ScreenEffect
}