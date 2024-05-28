package ru.braveowlet.simple_mvi_example.components.dogs.data.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class DogDto(
    val message: String,
    val status: String
)
