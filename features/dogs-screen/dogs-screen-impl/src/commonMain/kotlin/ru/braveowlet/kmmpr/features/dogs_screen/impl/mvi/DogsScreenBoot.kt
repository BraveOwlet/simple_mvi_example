package ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ru.braveowlet.kmmpr.common.mvi.general.MviBoot

internal class DogsScreenBoot : MviBoot<DogsScreenEffect> {
    override fun invoke(): Flow<DogsScreenEffect> = emptyFlow()
}