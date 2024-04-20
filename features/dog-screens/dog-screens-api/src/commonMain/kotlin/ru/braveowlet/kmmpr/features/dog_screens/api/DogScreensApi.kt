package ru.braveowlet.kmmpr.features.dog_screens.api

import cafe.adriel.voyager.core.screen.Screen

interface DogScreensApi {

    fun dogsScreen(): Screen

    fun savedDogsScreen(): Screen

    companion object {
        const val DOGS_SCREEN_TAG: String = "DogsScreen"
        const val SAVED_DOGS_SCREEN_TAG: String = "SavedDogsScreen"
    }
}