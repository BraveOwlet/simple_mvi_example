package ru.braveowlet.kmmpr.components.dogs.data.api

import ru.braveowlet.kmmpr.components.dogs.data.dto.DogDto
import ru.braveowlet.kmmpr.core.network.NetworkResult

internal interface DogsApi {
    suspend fun getRandomDog(): NetworkResult<DogDto>
}
