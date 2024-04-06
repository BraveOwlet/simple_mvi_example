package ru.braveowlet.kmmpr.features.dogs_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.common.mvi.koin.MviScreenModel
import ru.braveowlet.kmmpr.features.dogs_screen.api.DogsScreenApi
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenActor
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenModel

val dogsScreenModule
    get() = module {
        val tag = DogsScreenApi.TAG
        single<DogsScreenActor> { DogsScreenActor(get(), get()) }
        factory<MviScreenModel<*, *, *, *>>(qualifier(tag)) { DogsScreenModel(tag, get()) }
        single<DogsScreenApi> { DogsScreenImpl(tag) }
    }
