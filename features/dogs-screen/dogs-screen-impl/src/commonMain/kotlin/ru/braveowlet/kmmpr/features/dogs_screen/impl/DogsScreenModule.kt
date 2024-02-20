package ru.braveowlet.kmmpr.features.dogs_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.kmmpr.common.mvi.koin.MviScreenModel
import ru.braveowlet.kmmpr.features.dogs_screen.api.DogsScreenApi
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenActor
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenModel

val dogsScreenModule = module {
    single<DogsScreenActor> { DogsScreenActor(get()) }
    factory<MviScreenModel<*, *, *, *>>(qualifier(DogsScreenModel.tag)) { DogsScreenModel(get()) }
    single<DogsScreenApi> { DogsScreenImpl() }
}
