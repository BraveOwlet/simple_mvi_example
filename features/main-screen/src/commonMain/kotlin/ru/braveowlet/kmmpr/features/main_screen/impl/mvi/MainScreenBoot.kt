package ru.braveowlet.kmmpr.features.main_screen.impl.mvi

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.kmmpr.common.mvi.general.MviBoot

class MainScreenBoot : MviBoot<MainScreenEffect> {
    override fun invoke(): Flow<MainScreenEffect> = flow {
        delay(300)
        emit(MainScreenEffect.BootEffectMain("Boot-1"))
        delay(300)
        emit(MainScreenEffect.BootEffectMain("Boot-2"))
        delay(300)
        emit(MainScreenEffect.BootEffectMain("Boot-3"))
        delay(300)
        emit(MainScreenEffect.BootEffectMain("Boot-4"))
    }
}