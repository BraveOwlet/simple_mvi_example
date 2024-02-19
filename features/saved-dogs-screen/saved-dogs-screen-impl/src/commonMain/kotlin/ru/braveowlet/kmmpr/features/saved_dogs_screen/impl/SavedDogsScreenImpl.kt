package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.kmmpr.features.saved_dogs_screen.api.SavedDogsScreenApi

internal class SavedDogsScreenImpl : SavedDogsScreenApi {

    override fun savedDogsScreen(): Screen = SavedDogsScreen()
}