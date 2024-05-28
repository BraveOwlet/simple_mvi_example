package ru.braveowlet.simple_mvi_example.features.dog_screens.api

import cafe.adriel.voyager.core.screen.Screen

interface DogScreensApi {

    fun dogsScreen(): Screen

    fun savedDogsScreen(): Screen
}
