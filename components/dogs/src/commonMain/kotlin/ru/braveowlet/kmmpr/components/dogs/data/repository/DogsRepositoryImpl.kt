package ru.braveowlet.kmmpr.components.dogs.data.repository

import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import ru.braveowlet.kmmpr.components.dogs.data.api.getRandomImageWithDog
import ru.braveowlet.kmmpr.components.dogs.data.mapper.toDomain
import ru.braveowlet.kmmpr.components.dogs.domain.model.ImageWithDog
import ru.braveowlet.kmmpr.components.dogs.domain.repository.DogsRepository
import ru.braveowlet.kmmpr.core.network.NetworkResult
import ru.braveowlet.kmmpr.core.network.map

internal class DogsRepositoryImpl(
    private val httpClient: HttpClient
) : DogsRepository {

    override suspend fun getRandomImageWithDog(): NetworkResult<ImageWithDog> {
        delay(5000)
        return httpClient
            .getRandomImageWithDog()
            .map { it.toDomain() }

    }
}
