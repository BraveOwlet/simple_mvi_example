package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.common.mvi.koin.MviScreenModel
import ru.braveowlet.kmmpr.features.saved_dogs_screen.api.SavedDogsScreenApi
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenBoot
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi.SavedDogsScreenModel

val savedDogsScreenModule
    get() = module {
        val tag = SavedDogsScreenApi.TAG
        single<SavedDogsScreenBoot> { SavedDogsScreenBoot(get()) }
        factory<MviScreenModel<*, *, *, *>>(qualifier(tag)) { SavedDogsScreenModel(tag, get()) }
        single<SavedDogsScreenApi> { SavedDogsScreenImpl(tag) }
    }
