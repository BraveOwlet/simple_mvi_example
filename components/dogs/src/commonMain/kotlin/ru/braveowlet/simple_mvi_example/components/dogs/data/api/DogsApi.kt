package ru.braveowlet.simple_mvi_example.components.dogs.data.api

import io.ktor.client.HttpClient
import ru.braveowlet.simple_mvi_example.components.dogs.data.dto.DogDto
import ru.braveowlet.simple_mvi_example.core.network.fetchForGet

internal interface DogsApi {
    suspend fun getRandomDog(): Result<DogDto>
}

internal class DogsApiImpl(
    private val httpClient: HttpClient
) : DogsApi {

    override suspend fun getRandomDog(): Result<DogDto> =
        httpClient.fetchForGet<DogDto>("https://dog.ceo/api/breeds/image/random")
}
