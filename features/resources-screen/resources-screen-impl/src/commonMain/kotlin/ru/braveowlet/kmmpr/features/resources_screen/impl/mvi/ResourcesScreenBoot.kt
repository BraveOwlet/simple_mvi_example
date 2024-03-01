package ru.braveowlet.kmmpr.features.resources_screen.impl.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ru.braveowlet.common.mvi.general.MviBoot

internal class ResourcesScreenBoot : MviBoot<ResourcesScreenEffect> {
    override fun invoke(): Flow<ResourcesScreenEffect> = emptyFlow()
}
