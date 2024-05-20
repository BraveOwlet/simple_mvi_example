package ru.braveowlet.kmmpr.components.dogs.data.mapper

import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import ru.braveowlet.kmmpr.core.database.domain.dogs.DogEntity
import kotlin.random.Random

internal fun DogEntity.toDomain(): Dog = Dog(
    url = url
)

internal fun Dog.toEntity(): DogEntity = DogEntity(
    id = Random.nextLong(),
    url = url,
)
