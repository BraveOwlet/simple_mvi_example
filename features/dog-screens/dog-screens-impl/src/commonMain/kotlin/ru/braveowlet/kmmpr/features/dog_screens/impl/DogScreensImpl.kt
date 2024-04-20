package ru.braveowlet.kmmpr.features.dog_screens.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.kmmpr.features.dog_screens.api.DogScreensApi
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.view.DogsScreen
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.view.SavedDogsScreen

internal class DogScreensImpl(
    private val dogsScreenTag: String,
    private val savedDogsScreenTag: String
) : DogScreensApi {

    override fun dogsScreen(): Screen = DogsScreen(
        tag = dogsScreenTag
    )

    override fun savedDogsScreen(): Screen = SavedDogsScreen(
        tag = savedDogsScreenTag
    )
}
