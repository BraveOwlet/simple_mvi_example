package ru.braveowlet.kmmpr.components.dogs.data.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class ImageWithDogDto(
    val message: String,
    val status: String
)
