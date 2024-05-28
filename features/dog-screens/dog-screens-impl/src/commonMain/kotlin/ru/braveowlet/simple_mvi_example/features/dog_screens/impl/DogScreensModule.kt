package ru.braveowlet.simple_mvi_example.features.dog_screens.impl

import org.koin.dsl.module
import ru.braveowlet.common.mvi.impl.provideMviModel
import ru.braveowlet.simple_mvi_example.features.dog_screens.api.DogScreensApi
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.DogsScreen
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.dogs_screen.DogsScreenModel
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.SavedDogsScreen
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.screens.saved_dogs_screen.SavedDogsScreenModel

val dogScreensModule
    get() = module {
        provideMviModel<DogsScreen> { tag, _ -> DogsScreenModel(tag, get(), get()) }
        provideMviModel<SavedDogsScreen> { tag, _ -> SavedDogsScreenModel(tag, get()) }
        single<DogScreensApi> { DogScreensImpl() }
    }
