package ru.braveowlet.kmmpr.features.dogs_screen.api

import cafe.adriel.voyager.core.screen.Screen

interface DogsScreenApi {

    fun dogsScreen(): Screen

    companion object {
        const val TAG: String = "DogsScreen"
    }
}