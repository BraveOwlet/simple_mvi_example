package ru.braveowlet.kmmpr.features.main_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviEffect

sealed interface MainScreenEffect : MviEffect {
    data class BootEffectMain(val data: String) : MainScreenEffect
    data object ButtonClicked : MainScreenEffect
}