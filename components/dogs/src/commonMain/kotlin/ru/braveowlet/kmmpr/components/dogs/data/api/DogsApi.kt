package ru.braveowlet.kmmpr.components.dogs.data.api

import io.ktor.client.HttpClient
import ru.braveowlet.kmmpr.components.dogs.data.dto.ImageWithDogDto
import ru.braveowlet.kmmpr.core.network.fetchForGet

internal suspend fun HttpClient.getRandomImageWithDog() = fetchForGet<ImageWithDogDto>(
    url = "https://dog.ceo/api/breeds/image/random"
)
