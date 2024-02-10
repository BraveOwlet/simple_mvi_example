package ru.braveowlet.kmmpr.features.first_screen.mvi

import ru.braveowlet.kmmpr.common.mvi.MviState

data class ScreenState(
    val data: String,
    val logoIsShowed: Boolean,
): MviState