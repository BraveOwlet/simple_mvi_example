package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.common.mvi.koin.MviScreenModel
import ru.braveowlet.kmmpr.features.saved_dogs_screen.api.SavedDogsScreenApi
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenBoot
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenModel

val savedDogsScreenModule = module {
    single<SavedDogsScreenBoot> { SavedDogsScreenBoot(get()) }
    factory<MviScreenModel<*, *, *, *>>(qualifier(SavedDogsScreenModel.tag)) {
        SavedDogsScreenModel(get())
    }
    single<SavedDogsScreenApi> { SavedDogsScreenImpl() }
}
