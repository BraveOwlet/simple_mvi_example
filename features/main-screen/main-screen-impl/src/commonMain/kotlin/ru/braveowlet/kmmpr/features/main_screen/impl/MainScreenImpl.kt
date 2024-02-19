package ru.braveowlet.kmmpr.features.main_screen.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenApi

internal class MainScreenImpl : MainScreenApi {

    override fun mainScreen(): Screen = MainScreen()
}