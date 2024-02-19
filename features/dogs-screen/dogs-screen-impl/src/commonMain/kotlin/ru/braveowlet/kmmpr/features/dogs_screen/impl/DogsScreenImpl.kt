package ru.braveowlet.kmmpr.features.dogs_screen.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.kmmpr.features.dogs_screen.api.DogsScreenApi

internal class DogsScreenImpl : DogsScreenApi {

    override fun dogsScreen(): Screen = DogsScreen()
}