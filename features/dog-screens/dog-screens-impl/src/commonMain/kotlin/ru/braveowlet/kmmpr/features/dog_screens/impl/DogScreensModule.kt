package ru.braveowlet.kmmpr.features.dog_screens.impl

import org.koin.dsl.module
import ru.braveowlet.common.mvi.impl.createFactoryModel
import ru.braveowlet.kmmpr.features.dog_screens.api.DogScreensApi
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.DogsScreen
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.DogsScreenModel
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.SavedDogsScreen
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.SavedDogsScreenModel

val dogScreensModule
    get() = module {
        createFactoryModel<DogsScreen> { tag -> DogsScreenModel(tag, get(), get()) }
        createFactoryModel<SavedDogsScreen> { tag -> SavedDogsScreenModel(tag, get()) }
        single<DogScreensApi> { DogScreensImpl() }
    }
