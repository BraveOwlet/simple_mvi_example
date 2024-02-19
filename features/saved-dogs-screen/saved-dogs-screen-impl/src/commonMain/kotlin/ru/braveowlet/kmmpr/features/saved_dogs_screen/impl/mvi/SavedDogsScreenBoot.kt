package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ru.braveowlet.kmmpr.common.mvi.general.MviBoot

internal class SavedDogsScreenBoot : MviBoot<SavedDogsScreenEffect> {
    override fun invoke(): Flow<SavedDogsScreenEffect> = emptyFlow()
}