package ru.braveowlet.kmmpr.components.dogs.domain.repository

import ru.braveowlet.kmmpr.components.dogs.domain.model.ImageWithDog
import ru.braveowlet.kmmpr.core.network.NetworkResult

internal interface DogsRepository {
    suspend fun getRandomImageWithDog(): NetworkResult<ImageWithDog>
}
