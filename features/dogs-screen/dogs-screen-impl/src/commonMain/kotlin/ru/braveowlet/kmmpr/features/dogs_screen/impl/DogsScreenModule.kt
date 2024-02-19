package ru.braveowlet.kmmpr.features.dogs_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.kmmpr.common.mvi.koin.MviScreenModel
import ru.braveowlet.kmmpr.features.dogs_screen.api.DogsScreenApi
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenModel

val dogsScreenModule = module {
    factory<MviScreenModel<*, *, *, *>>(qualifier(DogsScreenModel.tag)) { DogsScreenModel() }
    single<DogsScreenApi> { DogsScreenImpl() }
}
