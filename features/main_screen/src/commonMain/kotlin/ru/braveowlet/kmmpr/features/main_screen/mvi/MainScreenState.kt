package ru.braveowlet.kmmpr.features.main_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviState

data class MainScreenState(
    val data: String,
    val logoIsShowed: Boolean,
): MviState