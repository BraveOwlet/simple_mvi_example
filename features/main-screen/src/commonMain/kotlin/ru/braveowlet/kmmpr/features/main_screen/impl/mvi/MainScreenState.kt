package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviState

data class MainScreenState(
    val data: String,
    val logoIsShowed: Boolean,
): MviState