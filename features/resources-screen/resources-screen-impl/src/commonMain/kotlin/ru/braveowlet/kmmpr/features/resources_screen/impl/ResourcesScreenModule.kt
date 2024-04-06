package ru.braveowlet.kmmpr.features.resources_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.common.mvi.koin.MviScreenModel
import ru.braveowlet.kmmpr.features.resources_screen.api.ResourcesScreenApi
import ru.braveowlet.kmmpr.features.resources_screen.impl.mvi.ResourcesScreenModel

val resourcesScreenModule
    get() = module {
        val tag = ResourcesScreenApi.TAG
        factory<MviScreenModel<*, *, *, *>>(qualifier(tag)) { ResourcesScreenModel(tag) }
        single<ResourcesScreenApi> { ResourcesScreenImpl(tag) }
    }
