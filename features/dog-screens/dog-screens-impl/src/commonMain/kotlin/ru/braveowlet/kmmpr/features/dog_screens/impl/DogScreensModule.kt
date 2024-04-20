package ru.braveowlet.kmmpr.features.dog_screens.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.features.dog_screens.api.DogScreensApi
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.model.DogsScreenModel
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.model.SavedDogsScreenModel

val dogScreensModule
    get() = module {
        val dogsScreenTag = DogScreensApi.DOGS_SCREEN_TAG
        val savedDogsScreenTag = DogScreensApi.SAVED_DOGS_SCREEN_TAG
        factory<MviModel<*, *, *, *>>(qualifier(dogsScreenTag)) {
            DogsScreenModel(dogsScreenTag, get(), get())
        }
        factory<MviModel<*, *, *, *>>(qualifier(savedDogsScreenTag)) {
            SavedDogsScreenModel(savedDogsScreenTag, get())
        }
        single<DogScreensApi> { DogScreensImpl(
            dogsScreenTag = dogsScreenTag,
            savedDogsScreenTag = savedDogsScreenTag
        ) }
    }
