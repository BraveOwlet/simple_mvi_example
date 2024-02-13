package ru.braveowlet.kmmpr.features.main_screen.mvi

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.kmmpr.common.mvi.MviBoot
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenEffect

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