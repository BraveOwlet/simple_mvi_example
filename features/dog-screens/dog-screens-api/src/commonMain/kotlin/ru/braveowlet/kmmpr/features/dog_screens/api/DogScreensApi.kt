package ru.braveowlet.kmmpr.features.dog_screens.api

import cafe.adriel.voyager.core.screen.Screen

interface DogScreensApi {

    fun dogsScreen(): Screen

    fun savedDogsScreen(): Screen
}