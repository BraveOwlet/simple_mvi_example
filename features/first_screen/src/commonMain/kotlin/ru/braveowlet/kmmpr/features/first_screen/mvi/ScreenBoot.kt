package ru.braveowlet.kmmpr.features.first_screen.mvi

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.braveowlet.kmmpr.common.mvi.MviBoot

class ScreenBoot : MviBoot<ScreenEffect> {
    override fun invoke(): Flow<ScreenEffect> = flow {
        delay(300)
        emit(ScreenEffect.BootEffect("Boot-1"))
        delay(300)
        emit(ScreenEffect.BootEffect("Boot-2"))
        delay(300)
        emit(ScreenEffect.BootEffect("Boot-3"))
        delay(300)
        emit(ScreenEffect.BootEffect("Boot-4"))
    }
}