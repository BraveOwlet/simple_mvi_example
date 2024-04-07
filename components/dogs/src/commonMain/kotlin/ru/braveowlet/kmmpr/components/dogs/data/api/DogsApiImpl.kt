package ru.braveowlet.kmmpr.components.dogs.data.api

import io.ktor.client.HttpClient
import ru.braveowlet.kmmpr.components.dogs.data.dto.DogDto
import ru.braveowlet.kmmpr.core.network.fetchForGet

internal class DogsApiImpl(
    private val httpClient: HttpClient
) : DogsApi {

    override suspend fun getRandomDog(): Result<DogDto> =
        httpClient.fetchForGet<DogDto>("https://dog.ceo/api/breeds/image/random")
}
