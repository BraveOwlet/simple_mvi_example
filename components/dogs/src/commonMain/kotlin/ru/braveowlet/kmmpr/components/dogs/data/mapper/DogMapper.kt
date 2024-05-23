package ru.braveowlet.kmmpr.components.dogs.data.mapper

import ru.braveowlet.kmmpr.components.dogs.data.dto.DogDto
import ru.braveowlet.kmmpr.components.dogs.data.entity.DogEntity
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import kotlin.random.Random

internal fun DogDto.toDomain(): Dog = Dog(
    url = this.message
)

internal fun DogEntity.toDomain(): Dog = Dog(
    url = url
)

internal fun Dog.toEntity(): DogEntity = DogEntity(
    url = url,
)
