package ru.braveowlet.simple_mvi_example.features.main_screen.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.simple_mvi_example.features.main_screen.api.MainScreenApi
import ru.braveowlet.simple_mvi_example.features.main_screen.impl.screens.main_screen.MainScreen

internal class MainScreenImpl : MainScreenApi {
    override fun mainScreen(): Screen = MainScreen()
}
