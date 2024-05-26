package ru.braveowlet.kmmpr.features.dog_screens.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.kmmpr.features.dog_screens.api.DogScreensApi
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.DogsScreen
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.SavedDogsScreen

internal class DogScreensImpl : DogScreensApi {

    override fun dogsScreen(): Screen = DogsScreen()

    override fun savedDogsScreen(): Screen = SavedDogsScreen()
}
