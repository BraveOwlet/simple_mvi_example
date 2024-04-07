package ru.braveowlet.kmmpr.components.dogs.data.api

import ru.braveowlet.kmmpr.components.dogs.data.dto.DogDto

internal interface DogsApi {
    suspend fun getRandomDog(): Result<DogDto>
}
