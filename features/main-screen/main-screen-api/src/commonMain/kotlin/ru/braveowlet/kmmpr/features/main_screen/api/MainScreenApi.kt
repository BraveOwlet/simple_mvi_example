package ru.braveowlet.kmmpr.features.main_screen.api

import cafe.adriel.voyager.core.screen.Screen

interface MainScreenApi {
    fun mainScreen() : Screen

    companion object {
        const val TAG: String = "MainScreen"
    }
}