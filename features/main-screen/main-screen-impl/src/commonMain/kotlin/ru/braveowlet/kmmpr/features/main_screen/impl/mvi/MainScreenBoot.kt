package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ru.braveowlet.kmmpr.common.mvi.general.MviBoot

internal class MainScreenBoot : MviBoot<MainScreenEffect> {
    override fun invoke(): Flow<MainScreenEffect> = emptyFlow()
}