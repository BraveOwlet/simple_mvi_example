package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.kmmpr.common.mvi.koin.MviScreenModel
import ru.braveowlet.kmmpr.features.saved_dogs_screen.api.SavedDogsScreenApi
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenModel

val savedDogsScreenModule = module {
    factory<MviScreenModel<*, *, *, *>>(qualifier(SavedDogsScreenModel.tag)) { SavedDogsScreenModel() }
    single<SavedDogsScreenApi> { SavedDogsScreenImpl() }
}
