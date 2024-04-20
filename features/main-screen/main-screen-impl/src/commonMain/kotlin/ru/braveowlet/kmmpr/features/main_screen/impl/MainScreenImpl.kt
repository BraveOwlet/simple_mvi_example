package ru.braveowlet.kmmpr.features.main_screen.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenApi
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.view.MainScreen

internal class MainScreenImpl(
    private val tag : String
) : MainScreenApi {
    override fun mainScreen(): Screen = MainScreen(tag)
}
